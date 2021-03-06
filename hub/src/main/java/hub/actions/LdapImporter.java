package hub.actions;

import com.google.common.base.Joiner;
import com.unboundid.ldap.sdk.*;
import hub.types.dynamic.User;
import hub.types.persistent.Person;
import hub.types.persistent.Username;
import org.springframework.beans.factory.annotation.Autowired;
import things.control.ThingAction;
import things.thing.ThingControl;
import things.control.TypeRegistry;
import things.exceptions.ThingRuntimeException;
import things.model.SingleStringValue;
import things.thing.Thing;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Imports users from current LDAP database
 */
public class LdapImporter implements ThingAction {

    @Autowired
    private ThingControl tc;

    /**
     * Connects to LDAP, looks up users and converts them into {@link hub.types.dynamic.User} objects.
     *
     * @return the list of users
     * @throws LDAPException if LDAP connection fails
     */
    public static List<User> retrieveLdapUsers() throws LDAPException {

        LDAPConnection con = new LDAPConnection("pan-ldap.uoa.nesi.org.nz", 389);

        SearchResult result = con.search("dc=uoa,dc=nesi,dc=org,dc=nz", SearchScope.SUB, "(&(objectClass=posixAccount)(gidNumber=5000))");

        List<User> users = result.getSearchEntries().stream()
                .map(entry -> createUser(entry))
                .filter(u -> u.isPresent())
                .map(Optional::get)
                .collect(Collectors.toList());

        con.close();

        return users;

    }

    /**
     * Assembles a {@link hub.types.dynamic.User} object from an LDAP search result entry.
     *
     * @param entry the LDAP search result
     * @return the assembled User object
     */
    private static Optional<User> createUser(SearchResultEntry entry) {

        User u = new User();
        String username = entry.getAttributeValue("uid");
        String gecos = entry.getAttributeValue("gecos");
        String dn = entry.getAttributeValue("description");
        String mail = entry.getAttributeValue("mail");
        String homeDir = entry.getAttributeValue("homedirectory");
        String uid = entry.getAttributeValue("uidnumber");
        String shell = entry.getAttributeValue("loginshell");
        
        Person p = new Person();
        p.setEmail(mail);
        String[] names = parseName(gecos);
        if ( names == null ) {
            return Optional.empty();
        }

        p.setFirst_name(names[0]);
        p.setMiddle_names(names[1]);
        p.setLast_name(names[2]);

        u.setPerson(p);

        u.addUsername("uoa", username);

        return Optional.of(u);
    }

    /**
     * Parses the gecos field.
     *
     * @param gecosField the field
     * @return String array with 3 Strings: first, middle and last name
     */
    private static String[] parseName(String gecosField) {

        String[] result = new String[3];
        String[] tokens = gecosField.split(" ");

        if ( tokens.length == 3 ) {
            result[0] = tokens[0];
            result[1] = "";
            result[2] = tokens[1];
        } else if (tokens.length > 3) {
            result[0] = tokens[0];
            result[1] = Joiner.on(" ").join(Arrays.copyOfRange(tokens, 1, tokens.length - 2));
            result[2] = tokens[tokens.length-2];
        } else {
            System.out.println("Could not parse: "+gecosField);
            return null;
        }

        return result;
    }

    @Override
    public String execute(String command, List<Thing> things, Map<String, String> parameters) {

        List<User> users;
        try {
            users = retrieveLdapUsers();
        } catch (LDAPException e) {
            throw new ThingRuntimeException("Could not lookup ldap users");
        }
        
        for ( User u : users ) {

            Person p = u.getPerson();
            String nesi_username = (p.getFirst_name()+"_"+p.getLast_name()).toLowerCase();
            Thing tp = null;
            try {
                tp = tc.createThing(nesi_username, p);
            } catch (Exception te) {
                System.out.println("Can't create thing for person "+p.nameToString()+": "+te.getLocalizedMessage());
                List<Thing> existing = tc.findThingsByTypeAndKey(Person.class, nesi_username);
                if ( existing.size() > 1 ) {
                    throw new ThingRuntimeException("Too many persons for key "+nesi_username);
                }
                tp = existing.get(0);
            }

            for ( String key : u.getUsernames().keySet() ) {
                for ( String un : u.getUsernames().get(key) ) {
                    try {
                        SingleStringValue username = TypeRegistry.createSingleStringValue(Username.class, un);
                        Stream<Thing> otherThingMatches = tc.getOtherThingsStreamByValue(tp, username);
                        if ( otherThingMatches.map(t -> tc.getUntypedValue(t)).anyMatch(v -> username.equals(v)) ) {
                            System.out.println("Username already in db: "+un);
                            continue;
                        }

                        Thing tu = tc.createThing(key, username);
                        tc.addThingToThing(tp, tu);
                    } catch (Exception e) {
                        throw new ThingRuntimeException("Could not import: "+un);
                    }
                }
            }
            System.out.println("All imported: "+tp.getKey());

        }

        return null;
    }


}
