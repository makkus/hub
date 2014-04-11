package things.model.types;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import things.external.types.persistent.Person;

/**
 * Project: hub
 * <p/>
 * Written by: Markus Binsteiner
 * Date: 20/03/14
 * Time: 11:39 AM
 */
public class PersonTest {
    @Test
    public void testHashCode() throws Exception {
        Person p1 = new Person("first1", "last1", "m.binsteiner@auckland.ac.nz");
        Person p2 = new Person("first1", "last2", "m.binsteiner@auckland.ac.nz");
        Person p3 = new Person("first1", "last1", "m.binsteiner@auckland.ac.nz");

        assertThat("Hashcode not equal", p1.hashCode() != p2.hashCode());
        assertThat("Hashcode equal", p1.hashCode() == p3.hashCode());
    }



    @Test
    public void testEquals() throws Exception {
        Person p1 = new Person("first1", "last1", "m.binsteiner@auckland.ac.nz");
        Person p2 = new Person("first1", "last2", "m.binsteiner@auckland.ac.nz");
        Person p3 = new Person("first1", "last1", "m.binsteiner@auckland.ac.nz");

        assertThat("Person not equal", (!p1.equals(p2)));
        assertThat("Person equal", (p1.equals(p3)));
    }
}
