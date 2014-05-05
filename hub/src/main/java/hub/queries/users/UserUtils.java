package hub.queries.users;

import com.google.common.collect.Lists;
import hub.types.dynamic.User;
import hub.types.persistent.Person;
import hub.types.persistent.Role;
import hub.types.persistent.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import things.control.TypeRegistry;
import things.exceptions.ThingRuntimeException;
import things.thing.Thing;
import things.thing.ThingControl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utilities to help manage, convert and lookup users, usernames and persons.
 *
 * @author: Markus Binsteiner
 */
public class UserUtils {

    @Autowired
    private ThingControl tc;

    /**
     * Converts a {@link Username} or {@link Person} thing into a list of {@link Username} things.
     *
     * Since a user can have multiple accounts for the same system (= key), multiple outputs are possible for
     * a single input.
     *
     * @param usernameOrPerson a thing of type Username or Person
     * @return list of all usernames
     */
    public List<Thing<Username>> lookupUsernames(Thing usernameOrPerson) {

        List<Thing<Username>> usernames = null;

        if ( StringUtils.isEmpty(usernameOrPerson.getThingType())) {
            throw new ThingRuntimeException(usernameOrPerson, "No type specified");
        }

        if ( TypeRegistry.equalsType(Person.class, usernameOrPerson.getThingType()) ) {

            usernames = tc.getOtherThingsByType(usernameOrPerson, Username.class);

        } else if ( TypeRegistry.equalsType(Username.class, usernameOrPerson.getThingType()) ) {
            usernames = Lists.newArrayList((Thing<Username>)usernameOrPerson);
        } else {
            throw new ThingRuntimeException("Can't lookup usernames using type: "+usernameOrPerson.getThingType());
        }

        return usernames;

    }

    /**
     * Converts a {@link Username} or {@link Person} thing into a {@link Person} thing.
     *
     * In case of a Person input, it will just get passwed through. In case of a Username input,
     * the database will be searched for the specified username & key and the parent Person thing
     * will be returned.
     *
     * If more than one results are found, a {@link things.exceptions.ThingRuntimeException} will be thrown.
     *
     * @param usernameOrPerson a thing of type Username or Person
     * @return the Person thing
     */
    public Thing<Person> lookupPerson(Thing usernameOrPerson) {

        Thing<Person> p = null;

        if ( TypeRegistry.equalsType(Username.class, usernameOrPerson.getThingType()) ) {
            //TODO
            List<Thing> parents = null;//tc.findThingsByOtherThingId(usernameOrPerson.getId());

            if ( parents.size() == 0 ) {
                throw new ThingRuntimeException(usernameOrPerson, "No person object found for username");
            } else if ( parents.size() > 1 ) {
                throw new ThingRuntimeException(usernameOrPerson, "Can't find unique person for username");
            }

            p = parents.iterator().next();
        } else if ( TypeRegistry.equalsType(Person.class, usernameOrPerson.getThingType()) ) {
            p = usernameOrPerson;
        }

        return p;

    }

    /**
     * Assembles a {@link User} object from a {@Person} one.
     *
     * @param p the person
     * @return the user thing object
     */
    public Thing<User> assembleUser(Thing<Person> p) {
        User user = new User();
        List<Thing<Username>> usernames = tc.getOtherThingsByType(p, Username.class);
        usernames.stream().forEach(un -> user.addUsername(un.getKey(), un.getValueId())); // can use valueid because we know it's a singlestringvalue

        List<Thing<Role>> roles = tc.getOtherThingsByType(p, Role.class);
        roles.stream().forEach(role -> user.addRole(role.getKey(), role.getValueId()));
        
        user.setPerson((Person) tc.getUntypedValue(p));

        return new Thing(p.getKey(), user);
    }

    /**
     * Takes every item in the input list,  and returns all usernames that are associated with it.
     *
     * @param usernamesOrPersons a list of username or person things used as queries (can contain globs for key and/or type)
     * @return a stream of all username objects that match the query
     */
	public Stream<Thing<Username>> getUsernameStream(List<Thing> usernamesOrPersons) {
        Stream<Thing<Username>> stream = usernamesOrPersons.stream().flatMap(t -> lookupUsernames(t).stream());
        return stream;
    }

    /**
     * Takes every item in the input list,  and returns all usernames that are associated with it.

     *
     * @param usernamesOrPersons a list of username or person things used as queries (can contain globs for key and/or type)
     * @return a list of all username objects that match the query
     */
	public List<Thing<Username>> getUsernames(List<Thing> usernamesOrPersons) {
		return getUsernameStream(usernamesOrPersons).collect(
				Collectors.toList());
	}

    /**
     * Takes every item in the input list,  and returns all persons that are associated with it.

     *
     * @param usernamesOrPersons a list of username or person things used as queries (can contain globs for key and/or type)
     * @return a stream of all {@link Person} objects that match the query
     */
	public Stream<Thing<Person>> getPersonStream(List<Thing> usernamesOrPersons) {
        Stream<Thing<Person>> tempThings = usernamesOrPersons.stream().map(t -> lookupPerson(t));

        return tempThings;
    }

    /**
     * Takes every item in the input list,  and returns all assembled User objects that are associated with it.

     *
     * @param usernamesOrPersons a list of username or person things used as queries (can contain globs for key and/or type)
     * @return a stream of all {@link User} objects that match the query
     */
	public Stream<Thing<User>> getUsersStream(List<Thing> usernamesOrPersons) {

        return getPersonStream(usernamesOrPersons).map(p -> assembleUser(p));
    }

    /**
     * Takes every item in the input list, and returns all assembled User objects that are associated with it.

     *
     * @param usernameOrPersons a list of username or person things used as queries (can contain globs for key and/or type)
     * @return a list of all {@link User} objects that match the query
     */
	public List<Thing<User>> getUsers(List<Thing> usernameOrPersons) {
		return getUsersStream(usernameOrPersons).collect(Collectors.toList());
	}



}
