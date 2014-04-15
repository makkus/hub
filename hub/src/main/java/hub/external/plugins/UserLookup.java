package hub.external.plugins;

import hub.types.dynamic.User;
import hub.types.persistent.Person;
import hub.types.persistent.Username;
import things.thing.ThingControl;
import things.control.ThingQuery;
import things.control.TypeRegistry;
import things.exceptions.NoSuchThingException;
import things.exceptions.ThingException;
import things.exceptions.ThingRuntimeException;
import things.thing.Thing;
import things.model.Value;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Project: things
 * <p>
 * Written by: Markus Binsteiner
 * Date: 9/04/14
 * Time: 12:17 PM
 */
public class UserLookup implements ThingQuery {

    public UserLookup() {
    }

    @Override
    public List<Thing> queryThings(ThingControl tc, List<Thing> usernamesOrPersons) {

        return usernamesOrPersons.stream().map(u -> lookupUser(tc, u)).collect(Collectors.toList());
    }

    public Thing lookupUser(ThingControl tc, Thing usernameOrPerson) {

        Value value = tc.getValue(usernameOrPerson);

        if ( value == null ) {
            if ( usernameOrPerson.getId() != null ) {
                usernameOrPerson = tc.findThingById(usernameOrPerson.getId());
            } else {
                try {
                    Optional<Thing> opt = tc.findUniqueThingByTypeAndKey(usernameOrPerson.getThingType(), usernameOrPerson.getKey());
                    if ( opt.isPresent()) {
                        usernameOrPerson = opt.get();
                    } else {
                        throw new NoSuchThingException("Can't find Person.", usernameOrPerson.getThingType(), usernameOrPerson.getKey(), usernameOrPerson.getId());
                    }
                    value = tc.getUntypedValue(usernameOrPerson);
                } catch (ThingException e) {
                    throw new ThingRuntimeException("Could not find a person or identity with type: "+usernameOrPerson.getThingType()+" and key: "+usernameOrPerson.getKey());
                }
            }
        }

        String key = usernameOrPerson.getKey();

        Thing<Person> p = null;

        if ( TypeRegistry.equalsType(Username.class, value) ) {

            List<Thing> things = tc.findThingsWithValue(value);

            Set<Thing> parents = things.parallelStream().filter(thing -> thing.getKey().equals(key)).flatMap(thing -> tc.findThingsByOtherThingId(thing.getId()).parallelStream()).collect(Collectors.toSet());

            if ( parents.size() == 0 ) {
                throw new ThingRuntimeException("No person object found for username: "+value);
            } else if ( parents.size() > 1 ) {
                throw new ThingRuntimeException("Can't find unique person for username: "+value+" and key: "+ usernameOrPerson.getKey());
            }

            p = parents.iterator().next();
        } else if ( TypeRegistry.equalsType(Person.class, value) ) {
            p = usernameOrPerson;
        }

        User user = new User();
        List<Thing<Username>> usernames = tc.getOtherThingsByType(p, Username.class);
        usernames.stream().forEach(un -> user.addUsername(un.getKey(), un.getValueId())); // can use valueid because we know it's a singlestringvalue

        user.setPerson((Person) tc.getUntypedValue(p));

        return new Thing(p.getKey(), user);
    }
}
