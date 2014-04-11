package things.control;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import things.config.GeneralConfiguration;
import things.exceptions.NoSuchThingException;
import things.exceptions.ThingException;
import things.model.Thing;
import hub.types.persistent.Person;
import hub.types.persistent.Role;
import hub.types.persistent.Username;


/**
 * Project: hub
 * <p/>
 * Written by: Markus Binsteiner
 * Date: 19/03/14
 * Time: 4:46 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ActiveProfiles(profiles = "testing")
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = {GeneralConfiguration.class})
//@Transactional
public class ThingControlTest {
    
    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private ThingControl thingControl;
    
    @Before
    public void setUp() throws Exception {
        try {
            mongoOperations.dropCollection(Person.class);
            mongoOperations.dropCollection(Thing.class);
            mongoOperations.dropCollection(Username.class);
            mongoOperations.dropCollection(Role.class);
        } catch (Exception e) {

        }

        for (int i=1;i<10;i++) {

            Username i1 = new Username("test_"+i+"_identity_1");
            Username i2 = new Username("test_"+i+"_identity_2");

            Thing ti = thingControl.createThing("id1_" + i, i1);
            Thing t2 = thingControl.createThing("id2_" + i, i2);


            Person p = new Person("Testee", "Tester", "m.binsteiner@auckland.ac.nz");
            Thing t = thingControl.createThing("testThing_" + i, p);

            thingControl.addThingToThing(t, ti);
            thingControl.addThingToThing(t, t2);

        }
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddThingToThing() throws Exception {

        Username i = new Username("identity");
        Thing ti = thingControl.createThing("id", i);

        Person p = new Person("Firstname", "Lastname", "m.binsteiner@auckland.ac.nz");
        Thing tp = thingControl.createThing("person", p);

        thingControl.addThingToThing(tp, ti);

        Thing newThing = thingControl.findThingById(tp.getId());

        assertThat("thing contains child id", newThing.getOtherThings().contains(ti.getId()));
    }

//    @Test(expected = ThingException.class)
//    public void testAddThingToThingWhereKeyAndTypeAlreadyExists() throws Exception {
//
//        Identity i = new Identity("identity");
//        Thing ti = thingControl.createThing("id", i);
//
//        Person p = new Person("Firstname", "Lastname");
//        Thing tp = thingControl.createThing("person", p);
//
//        thingControl.addThingToThing(tp, ti);
//
//        Thing newThing = thingControl.findThingById(tp.getId());
//
//        Identity i2 = new Identity("identity2");
//        Thing ti2 = thingControl.createThing("id", i2);
//
//        thingControl.addThingToThing(tp, ti2);
//    }

    @Test
    public void testAddThingToThingWhereThingAlreadyContainsThatThing() throws Exception {

        Username i = new Username("identity");
        Thing ti = thingControl.createThing("id", i);

        Person p = new Person("Firstname", "Lastname", "m.binsteiner@auckland.ac.nz");
        Thing tp = thingControl.createThing("person", p);

        thingControl.addThingToThing(tp, ti);

        Thing newThing = thingControl.findThingById(tp.getId());

        thingControl.addThingToThing(tp,ti);

        assertThat("thing contains child id", newThing.getOtherThings().contains(ti.getId()));
        assertThat("thing only has 1 other thing", newThing.getOtherThings().size() == 1 );
    }

    @Test
    public void testAddThingToThingWhereThingCanHaveMultipleOtherThingsWithSameKeyAndType() throws Exception {

        Person p = new Person("Firstname", "Lastname", "m.binsteiner@auckland.ac.nz");
        Thing tp = thingControl.createThing("person", p);


        Role role1 = new Role("admin");
        Thing role1Thing = thingControl.createThing("uoa00001", role1);

        Role role2 = new Role("admin");
        Thing role2Thing = thingControl.createThing("uoa00001", role2);

        thingControl.addThingToThing(tp, role1Thing);
        thingControl.addThingToThing(tp, role2Thing);

        Thing newThing = thingControl.findThingById(tp.getId());

        List<Thing> otherThings = thingControl.getOtherThingsByTypeAndKey(newThing, "role", "uoa00001");

        assertThat("Multiple other things exist", otherThings.size() == 2 );

    }

    @Test
    public void testFindThingById() throws Exception {

        Person p = new Person("Firstname", "Lastname", "m.binsteiner@auckland.ac.nz");
        Thing tp = thingControl.createThing("person", p);

        Thing newThing = thingControl.findThingById(tp.getId());

        assertThat("created thing id equals queried thing id", tp.getId().equals(newThing.getId()));
        Person pnew = (Person) thingControl.getValue(newThing);
        assertThat("created thing first name  equals queried thing first name", pnew.getFirst_name().equals(p.getFirst_name()));
        assertThat("created thing last name  equals queried thing last name", pnew.getLast_name().equals(p.getLast_name()));
    }

    @Test(expected = NoSuchThingException.class)
    public void testFindThingByIdNonExistent() throws Exception {

        Thing newThing = thingControl.findThingById("xxx");


    }

    @Test
    public void testCreateThing() throws Exception {
        Person p = new Person("Markus", "Binsteiner_XXX", "m.binsteiner@auckland.ac.nz");

        Thing t = thingControl.createThing("markus_xxx", p);

        List<Thing> things = thingControl.findThingsByTypeAndKey(p.getClass(), "markus_xxx");
        assertThat("Only one result", things.size() == 1);

        assertThat("Person element is the same that was created", thingControl.getValue(things.get(0)).equals(p));
    }

    @Test(expected = ThingException.class)
    public void testCreateThingWhereKeyAlreadyExistsAndTypeDoesNotAllowThat() throws Exception {
        Person p = new Person("Markus", "Binsteiner_XXX", "m.binsteiner@auckland.ac.nz");

        Thing t = thingControl.createThing("testThing_1", p);

        List<Thing> allThings = thingControl.findAllThings();

        for (Thing a : allThings) {
            System.out.println(a);
        }
    }

//    @Test
//    public void testFindThingByTypeAndId() throws Exception {
//
//        Person p = new Person("Markus", "Binsteiner_XXX");
//
//        Thing t = thingControl.createThing("markus_xxx", p);
//
//        List<Thing> thing = thingControl.findThingsByTypeAndValueId(Person.class, p.getId());
//
//        assertThat("Thing was created in db", thing != null);
//        Person pnew = (Person)thingControl.getValue(thing);
//        assertThat("Thing first name equals queried thing first name", pnew.getFirst_name().equals(p.getFirst_name()));
//        assertThat("Thing last name equals queried thing last name", pnew.getLast_name().equals(p.getLast_name()));
//        assertThat("Queried thing key equals old key", thing.getKey().equals("markus_xxx"));
//
//    }


//    @Test
    public void testFindThingsByValue() throws Exception {

        Person p = new Person("Markus", "Binsteiner_XXX", "m.binsteiner@auckland.ac.nz");
        Thing t = thingControl.createThing("markus_xxx", p);

        Person p2 = new Person("Markus", "Binsteiner_XXX", "m.binsteiner@auckland.ac.nz");

        List<Thing> thing = thingControl.findThingsWithValue(p2);

        assertThat("Result only has one item", thing.size() == 1);
        Person p3 = (Person) thingControl.getValue(thing.get(0));
        assertThat("Result first name equals original first name", p3.getFirst_name().equals(p.getFirst_name()));
        assertThat("Result last name equals original last name", p3.getLast_name().equals(p.getLast_name()));
    }

    @Test
    public void testFindThingsByKeyAndType() throws Exception {

        Person p = new Person("Markus", "Binsteiner_XXX", "m.binsteiner@auckland.ac.nz");
        Thing t = thingControl.createThing("markus_xxx", p);

        List<Thing> thing = thingControl.findThingsByTypeAndKey(Person.class, "markus_xxx");

        assertThat("Result only has one item", thing.size() == 1);
        Person p3 = (Person) thingControl.getValue(thing.get(0));
        assertThat("Result first name equals original first name", p3.getFirst_name().equals(p.getFirst_name()));
        assertThat("Result last name equals original last name", p3.getLast_name().equals(p.getLast_name()));
    }

    @Test
    public void testFindThingsByType() throws Exception {
        List<Thing> things = thingControl.findThingsByType(Person.class);

        assertThat("There are the right amount of things in the result set", things.size() == 9);
        for (Thing t : things) {
            assertThat("Result is of right type", TypeRegistry.getTypeClass(t.getThingType()).equals(Person.class));
        }
    }

//    @Test
//    public void testFindThingsByTypeAndValueIds() throws Exception {
//
//        List<Thing> things = thingControl.findThingsByType(Person.class);
//        List<String> ids = Lists.newArrayList();
//        for (Thing t : things) {
//            ids.add(((PersistentValue)t.getValue()).getId());
//        }
//
//        List<Thing> thingsNew = thingControl.findThingsByTypeAndValueIds(Person.class, ids);
//
//        assertThat("There are the right amount of things in the result set", things.size() == 9);
//        for (Thing t : things) {
//            assertThat("Result is of right type", TypeRegistry.getType(t).equals(TypeRegistry.getType(Person.class)));
//        }
//        assertThat("Two lists are equal", things.containsAll(thingsNew));
//        assertThat("Two lists are equal", thingsNew.containsAll(things));
//    }

//    @Test
//    public void testGetOtherThings() throws Exception {
//
//        Optional<Thing> thing = thingControl.findUniqueThingByTypeAndKey(Person.class, "testThing_1");
//
//        assertThat("Thing has 2 other thing references", thing.getOtherThings().size() == 2);
//
//        List<Thing> otherThings = thingControl.getOtherThings(thing);
//
//        assertThat("Thing has 2 other things", otherThings.size() == 2);
//        for ( Thing t : otherThings ) {
//            assertThat("Thing is of type identity", TypeRegistry.getTypeClass(t.getThingType()).equals(Username.class));
//            Username username = (Username)thingControl.getValue(t);
//            assertThat("Thing value contains word _identity_", username.getValue().contains("_identity_"));
//        }
//
//    }

    @Test
    public void testGetTypeClass() throws Exception {

        assertThat("Type Identity maps to collection identity", TypeRegistry.getType(Username.class).equals("username"));
        assertThat("Collection identity maps to Type Identity", TypeRegistry.getTypeClass("username").equals(Username.class));

    }

    @Test
    public void testGetOtherThingsByType() throws Exception {

        Optional<Thing> thing = thingControl.findUniqueThingByTypeAndKey(Person.class, "testThing_1");

        assertThat("thing exists", thing.isPresent());

        List<Thing> otherThings = thingControl.getOtherThingsByType(thing.get(), Username.class);
        assertThat("Thing has 2 other things", otherThings.size() == 2);
        for ( Thing t : otherThings ) {
            assertThat("Thing is of type identity", TypeRegistry.getTypeClass(t.getThingType()).equals(Username.class));
            Username username = (Username)thingControl.getValue(t);
            assertThat("Thing value contains word _identity_", username.getValue().contains("_identity_"));
        }
    }

    @Test
    public void testPersistentValueNeedsUniqueKey() throws Exception {

        assertThat("Class person needs value key", TypeRegistry.persistentValueNeedsUniqueKey("person"));

    }

//    @Test
//    public void testSaveAndLoadPersistentValue() throws Exception {
//        Person p = new Person("Markus", "Binsteiner_XXX");
//        assertThat("Persistent value has no id", p.getId() == null);
//        thingControl.saveValue(p);
//
//        assertThat("Persistent value has id", p.getId() != null);
//
//        PersistentValue v = thingControl.loadPersistentValue(Person.class, p.getId());
//        assertThat("Result is not null", v != null);
//        assertThat("Result is of type person", (v.getClass().equals(Person.class)));
//        Person pnew = (Person) v;
//        assertThat("New first name equals original first name", pnew.getFirst_name().equals(p.getFirst_name()));
//        assertThat("New last name equals original last name", pnew.getLast_name().equals(p.getLast_name()));
//    }

    @Test
    public void testThingHasOtherThing() throws Exception {
        Optional<Thing> thing = thingControl.findUniqueThingByTypeAndKey(Person.class, "testThing_1");
        Optional<Thing> thing_identity = thingControl.findUniqueThingByTypeAndKey(Username.class, "id1_1");

        assertThat("Person has identity among other things", thingControl.thingHasOtherThing(thing.get(), thing_identity.get().getId()));
    }

    @Test
    public void testThingHasOtherThingWithKeyAndType() throws Exception {
        Optional<Thing> thing = thingControl.findUniqueThingByTypeAndKey(Person.class, "testThing_1");
//        Optional<Thing> thing_identity = thingControl.findUniqueThingByTypeAndKey(Username.class, "id1_1");

        assertThat("Person has thing with type identity and key id1_1", thingControl.thingHasOtherThingWithTypeAndKey(thing.get(), TypeRegistry.getType(Username.class), "id1_1"));
    }

    @Test
    public void testThingWithKeyAndTypeExists() throws Exception {

        assertThat("Thing with key testThing_1 and type person exists", thingControl.thingWithTypeAndKeyExists(Person.class, "testThing_1"));

    }
}
