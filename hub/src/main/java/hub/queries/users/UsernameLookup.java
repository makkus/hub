package hub.queries.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import things.control.ThingQuery;
import hub.types.persistent.Username;
import things.exceptions.QueryException;
import things.thing.Thing;

import java.util.List;
import java.util.Map;

/**
 * @author: Markus Binsteiner
 */
public class UsernameLookup implements ThingQuery<Username> {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<Thing<Username>> query(List<Thing> things, Map<String, String> queryParams) throws QueryException {


        return null;
    }
}
