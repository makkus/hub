package things.control;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import things.config.GeneralConfiguration;
import things.control.mongo.MongoThingControl;
import things.exceptions.ThingException;
import things.external.types.persistent.Person;
import things.external.types.persistent.Username;
import things.model.Thing;

import com.google.common.collect.Maps;

/**
 * Project: hub
 * <p/>
 * Written by: Markus Binsteiner
 * Date: 13/03/14
 * Time: 11:29 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ActiveProfiles(profiles = "testing")
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = {GeneralConfiguration.class})
//@Transactional
public class ThingTest {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private MongoThingControl thingControl;
    
    private Map<String, Thing> things = Maps.newHashMap();
    private Map<String, Person> persons = Maps.newHashMap();
    
    @Before
    public void init() throws Exception {


        mongoOperations.dropCollection(Person.class);
        mongoOperations.dropCollection(Thing.class);
        mongoOperations.dropCollection(Username.class);

        for (int i=1;i<10;i++) {

            Username i1 = new Username("mbin029_"+i+"_identity_1");
            Username i2 = new Username("mbin029_"+i+"_identity_2");

            Thing ti = thingControl.createThing("id1_" + i, i1);
            Thing t2 = thingControl.createThing("id2_" + i, i2);


            Person p = new Person("Markus", "Binsteiner", "m.binsteiner@auckland.ac.nz");
            Thing t = thingControl.createThing("markus_" + i, p);

            things.put(t.getId(), t);
            persons.put(p.getId(), p);

            thingControl.addThingToThing(t, ti);
            thingControl.addThingToThing(t, t2);
        }

    }
    

    @Test(expected = ThingException.class)
    public void testCreateExistingThingWhenUniqueKeyForClass() throws Exception {
        Person p = new Person("Markus", "Binsteiner_XXX", "m.binsteiner@auckland.ac.nz");
        Thing t = thingControl.createThing("markus_1", p);
    }

    @Test
    public void testCreateExistingThingWhenNonUniqueKeyForClass() throws Exception {
        Username i = new Username("mbin029_xxx");
        Thing t = thingControl.createThing("id1_1", i);

        List<Thing> things = thingControl.findThingsByTypeAndKey(Username.class, "id1_1");

        assertThat("Two things with same key and type exist", things.size() == 2);

        assertThat("Two things are equal", things.get(0), not(things.get(1)));
    }

    @Test
    public void testThingEqualsOtherThing() throws Exception {
        Person p = new Person("Markus", "Binsteiner", "m.binsteiner@auckland.ac.nz");
        Thing t = thingControl.createThing("markus_xxx", p);

        Optional<Thing> old = thingControl.findUniqueThingByTypeAndKey(Person.class, "markus_1");

        assertThat("two persons are different even though they have same first and last name", (!t.equals(old.get())));
    }

    @Test
    public void testThingHashCode() throws Exception {
        Person p = new Person("Markus", "Binsteiner", "m.binsteiner@auckland.ac.nz");
        Thing t = thingControl.createThing("markus_xxx", p);

        Optional<Thing> old = thingControl.findUniqueThingByTypeAndKey(Person.class, "markus_1");

        assertThat("two persons hashcodes are different even though they have same first and last name", t.hashCode() != old.get().hashCode());

    }

    @Test
    public void testFindAllThings() throws Exception {

        List<Thing> things = thingControl.findAllThings();

        assertThat("All things are there", things.size() == 27);

    }
    
}
