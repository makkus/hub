package hub.external.connectors;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import things.control.ThingReader;
import things.control.ThingWriter;
import things.control.TypeRegistry;
import things.model.PersistentValue;
import things.model.Thing;
import things.model.Value;
import things.utils.MatcherUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Project: things
 * <p>
 * Written by: Markus Binsteiner Date: 2/04/14 Time: 9:16 AM
 */
public class MongoConnector implements ThingReader, ThingWriter {

    private static final Logger myLogger = LoggerFactory.getLogger(MongoConnector.class);

    private final MongoOperations mo;

    public MongoConnector(MongoOperations mo) {
        this.mo = mo;
    }

//    public List<Thing> findThingsByObjectIds(Collection<String> ids) {
//
//        Query q = new Query();
//
//        q.addCriteria(Criteria.where("valueId").in(ids));
//
//        List<Thing> result = mo.find(q, Thing.class);
//
//        return result;
//
//    }



    @Override
    public Set<String> lookupThingsForValue(Value value) {

        Class<?> typeClass = value.getClass();

        BasicDBObject mongoDbObject = (BasicDBObject) mo.getConverter()
                .convertToMongoType(value);

        // remove non-populated (null) fields
        List<String> keysToRemove = Lists.newLinkedList();
        for (String key : mongoDbObject.keySet()) {
            if (mongoDbObject.get(key) == null) {
                keysToRemove.add(key);
            }
        }
        for (String key : keysToRemove) {
            mongoDbObject.remove(key);
        }

        BasicQuery bq = new BasicQuery(mongoDbObject.toString());

        List<PersistentValue> listWithIds = (List<PersistentValue>) mo.find(bq, typeClass);

        Set<String> ids = listWithIds.stream().map(v -> v.getId()).collect(Collectors.toSet());

        return ids;
        
//        List<Thing> result = findThingsByObjectIds(ids);
//
//        return result;
    }


    //@Override
    public List<Thing> getOtherThingsByTypeAndKey(Thing parent, String type, String key) {

        String typeRegex = MatcherUtils.convertGlobToRegex(type);
        Query q = new Query();

        q.addCriteria(Criteria.where("_id").in(parent.getOtherThings())
                .and("type").regex(typeRegex));
        List<Thing> result = mo.find(q, Thing.class);

        //TODO can we have 2 different queries?

        if ( "*".equals(key) ) {
            return result;
        } else {
            return result.parallelStream().filter(thing -> MatcherUtils.wildCardMatch(thing.getKey(), key)).collect(Collectors.toList());
        }

    }

    public PersistentValue loadPersistentValue(
            Class<? extends Value> typeClass, String id) {

        Query q = new Query();
        q.addCriteria(Criteria.where("_id").is(new ObjectId(id)));

        PersistentValue v = (PersistentValue) mo.findOne(q, typeClass);

        return v;

    }

    public String saveValue(PersistentValue pv) {

        myLogger.debug("Saving persistent value {}", pv.getId());

        mo.save(pv);

        return pv.getId();
    }

    @Override
    public PersistentValue readValue(String type, String valueId) {

        return loadPersistentValue(TypeRegistry.getTypeClass(type), valueId);

    }
}
