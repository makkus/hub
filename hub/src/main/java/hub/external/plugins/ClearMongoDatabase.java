package hub.external.plugins;

import hub.types.persistent.Person;
import hub.types.persistent.Role;
import hub.types.persistent.Username;
import org.springframework.data.mongodb.core.MongoTemplate;
import things.control.ThingAction;
import things.control.ThingControl;
import things.model.Thing;

import java.util.List;

/**
 * @author: Markus Binsteiner
 */
public class ClearMongoDatabase implements ThingAction {

    private MongoTemplate mo;

    public ClearMongoDatabase(MongoTemplate mo) {
        this.mo = mo;
    }

    @Override
    public boolean execute(ThingControl tc, List<Thing> things) {
        
        mo.dropCollection(Person.class);
        mo.dropCollection(Thing.class);
        mo.dropCollection(Username.class);
        mo.dropCollection(Role.class);

        return true;

    }
}
