package things.model.types;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import things.external.types.persistent.Username;

/**
 * Project: hub
 * <p/>
 * Written by: Markus Binsteiner
 * Date: 20/03/14
 * Time: 11:32 AM
 */
public class UsernameTest {
    @Test
    public void testHashCode() throws Exception {
        Username id1 = new Username("id1");
        Username id2 = new Username("id2");
        Username id3 = new Username("id1");

        assertThat("Hash code is different for different usernames", id1.hashCode() != id2.hashCode());
        assertThat("Hash code is equal for same usernames", id1.hashCode() == id3.hashCode());
    }

    @Test
    public void testEquals() throws Exception {
        Username id1 = new Username("id1");
        Username id2 = new Username("id2");
        Username id3 = new Username("id1");

        assertThat("Not equal for different usernames", (!id1.equals(id2)));
        assertThat("Not equal for different usernames", id1.equals(id3));
    }


}
