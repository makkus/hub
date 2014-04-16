package hub.queries;

import com.google.common.collect.Lists;
import hub.types.dynamic.User;
import hub.types.persistent.Person;
import hub.types.persistent.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import things.control.ThingQuery;
import things.control.TypeRegistry;
import things.exceptions.NoSuchThingException;
import things.exceptions.ThingRuntimeException;
import things.model.Value;
import things.thing.Thing;
import things.thing.ThingControl;
import things.utils.MatcherUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Project: things
 * <p>
 * Written by: Markus Binsteiner Date: 9/04/14 Time: 12:17 PM
 */
public class UserLookup implements ThingQuery<User> {

	@Autowired
	private ThingControl tc;

	public UserLookup() {
	}

	public List<Thing> resolvePotentialGlobs(Thing usernameOrPerson) {
		List<Thing> personsOrUsernamesToLookup = null;

		if (usernameOrPerson.getId() != null
				|| usernameOrPerson.getValueId() != null) {

			return Lists.newArrayList(usernameOrPerson);
		}

		if (usernameOrPerson.getId() != null) {
			usernameOrPerson = tc.findThingById(usernameOrPerson.getId());
		} else {
			List<Thing> opt = tc.findThingsByTypeAndKey(
					usernameOrPerson.getThingType(), usernameOrPerson.getKey());
			if (opt.size() > 0) {
				personsOrUsernamesToLookup = opt;
			} else {
				throw new NoSuchThingException("Can't find Person.",
						usernameOrPerson.getThingType(),
						usernameOrPerson.getKey(), usernameOrPerson.getId());
			}
		}
		return personsOrUsernamesToLookup;
	}

    public List<Thing<Username>> lookupUsernames(Thing usernameOrPerson) {

        List<Thing<Username>> usernames = null;

        if ( StringUtils.isEmpty(usernameOrPerson.getThingType())) {
            throw new ThingRuntimeException(usernameOrPerson, "No type specified");
        }

        if ( TypeRegistry.equalsType(Person.class, usernameOrPerson.getThingType()) ) {

            String key = usernameOrPerson.getKey();

            List<Thing<Person>> personThings = null;
            if ( ! StringUtils.isEmpty(usernameOrPerson.getId()) ) {
                Thing<Person> person = tc.findThingById(usernameOrPerson.getId());
                if ( person == null ) {
                    throw new ThingRuntimeException("Could not find person with thing-id: "+usernameOrPerson.getId());
                }
                if ( ! StringUtils.isEmpty(key) ) {
                    if (MatcherUtils.wildCardMatch(person.getKey(), key)) {
                        throw new ThingRuntimeException("Found key "+person.getKey()+" for id: "+usernameOrPerson.getId()+" does not match key: "+key);
                    }
                }
                personThings = Lists.newArrayList(person);
            } else if (!StringUtils.isEmpty(usernameOrPerson.getKey())) {
            } else {
                Value value = tc.getValue(usernameOrPerson);
                personThings = tc.findThingsWithValue((Person)value);
            }

            if ( personThings.size() == 0 ) {
                throw new ThingRuntimeException(usernameOrPerson, "No person object found for: "+usernameOrPerson.getThingType()+"/"+usernameOrPerson.getKey());
            }

            usernames = personThings.stream().flatMap(p -> tc.getOtherThingsByType(p, Username.class).stream()).collect(Collectors.toList());

        } else if ( TypeRegistry.equalsType(Username.class, usernameOrPerson.getThingType()) ) {
            usernames = Lists.newArrayList(usernameOrPerson);
        }

        return usernames;

    }

	public Thing<Person> lookupPerson(Thing usernameOrPerson) {

        Value value = tc.getValue(usernameOrPerson);

        Thing<Person> p = null;

        if ( TypeRegistry.equalsType(Username.class, value) ) {

            String key = usernameOrPerson.getKey();

            List<Thing<Username>> things = tc.findThingsWithValue((Username)value);

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

        return p;

    }

    public Thing<User> assembleUser(Thing<Person> p) {
        User user = new User();
        List<Thing<Username>> usernames = tc.getOtherThingsByType(p, Username.class);
        usernames.stream().forEach(un -> user.addUsername(un.getKey(), un.getValueId())); // can use valueid because we know it's a singlestringvalue

        user.setPerson((Person) tc.getUntypedValue(p));

        return new Thing(p.getKey(), user);
    }

    public Stream<Thing<Username>> getUsernameStream(List<Thing> usernamesOrPersons) {
        Stream<Thing> stream = usernamesOrPersons.stream()
                .flatMap(u -> resolvePotentialGlobs(u).stream());

        return stream.flatMap(t -> lookupUsernames(t).stream());

    }

    public List<Thing<Username>> getUsernames(List<Thing> usernamesOrPersons) {
        return getUsernameStream(usernamesOrPersons).collect(Collectors.toList());
    }


    public Stream<Thing<Person>> getPersonStream(List<Thing> usernamesOrPersons) {
        Stream<Thing> tempThings = usernamesOrPersons.stream()
                .flatMap(u -> resolvePotentialGlobs(u).stream());
        return tempThings.map(t -> lookupPerson(t));
    }

    public Stream<Thing<User>> getUsersStream(List<Thing> usernamesOrPersons) {

        return getPersonStream(usernamesOrPersons).map(p -> assembleUser(p));
    }

    public List<Thing<User>> getUsers(List<Thing> usernameOrPersons) {
        return getUsersStream(usernameOrPersons).collect(Collectors.toList());
    }

	@Override
    public List<Thing<User>> queryThings(List<Thing> usernamesOrPersons) {
        return getUsers(usernamesOrPersons);
    }
}
