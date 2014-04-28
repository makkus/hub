package hub.types.dynamic;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import hub.types.persistent.Person;

import java.io.Serializable;

/**
 * Project: things
 * <p>
 * Written by: Markus Binsteiner Date: 31/03/14 Time: 4:21 PM
 */
public class User implements Serializable {

//    private String uniqueId;
    private Person person;
    private Multimap<String, String> usernames = ArrayListMultimap.create();

    public User() {
    }

    public Person getPerson() {
        return person;
    }

//    public String getUniqueId() {
//        return uniqueId;
//    }

//    public void setUniqueId(String uniqueId) {
//        this.uniqueId = uniqueId;
//    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Multimap<String, String> getUsernames() {
        return usernames;
    }

    public void setUsernames(Multimap<String, String> usernames) {
        this.usernames = usernames;
    }
    
    public void addUsername(String key, String id) {
        this.usernames.put(key, id);
    }
}
