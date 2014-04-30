package hub.queries.users;

import hub.types.dynamic.User;
import hub.types.persistent.Person;
import hub.types.persistent.Username;
import org.springframework.beans.factory.annotation.Autowired;
import things.control.ThingQuery;
import things.thing.Thing;
import things.thing.ThingControl;

import java.util.List;
import java.util.Map;

/**
 * A query to lookup users using either {@link Person} or {@link Username}
 * objects and subsequently assemble {@link hub.types.dynamic.User} objects.
 */
public class UserLookup implements ThingQuery<User> {

	@Autowired
	private ThingControl tc;

    @Autowired
    private UserUtils utils;

	public UserLookup() {
	}

	@Override
	public List<Thing<User>> query(List<Thing> usernamesOrPersons, Map<String, String> params) {
		return utils.getUsers(usernamesOrPersons);
	}
}
