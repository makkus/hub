package hub.actions;

import hub.types.persistent.Person;
import hub.types.persistent.Role;
import hub.types.persistent.Username;
import org.springframework.data.mongodb.core.MongoTemplate;
import things.control.ThingAction;
import things.thing.Thing;

import java.util.List;
import java.util.Map;

/**
 * @author: Markus Binsteiner
 */
public class ClearMongoDatabase implements ThingAction {

    private MongoTemplate mo;

    public ClearMongoDatabase(MongoTemplate mo) {
        this.mo = mo;
    }


    @Override
    public String execute(String command, List<Thing> things, Map<String, String> parameters) {
        mo.dropCollection(Person.class);
        mo.dropCollection(Thing.class);
        mo.dropCollection(Username.class);
        mo.dropCollection(Role.class);

        return null;
    }
}
