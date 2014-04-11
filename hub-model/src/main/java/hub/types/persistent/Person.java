package hub.types.persistent;

import java.util.Objects;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import things.model.PersistentValue;
import things.model.types.attributes.UniqueKey;

import com.google.common.base.Strings;

/**
 * Project: hub
 * <p/>
 * Written by: Markus Binsteiner
 * Date: 12/03/14
 * Time: 1:51 PM
 */
@UniqueKey(unique = true)
//@Document(collection = "person")
public class Person implements PersistentValue {

//    @Id
    String id;

    @NotEmpty
    private String first_name;
    @NotEmpty
    private String last_name;

    private String middle_names = "";

    @Email
    private String email;

    public Person(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getMiddle_names() {
        return middle_names;
    }

    public void setMiddle_names(String middle_names) {
        this.middle_names = middle_names;
    }

    public Person() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {

        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;

        if (getClass().equals(obj.getClass())) {
            final Person other = (Person) obj;
            return Objects.equals(getFirst_name(), other.getFirst_name()) && Objects.equals(getLast_name(), other.getLast_name());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(getFirst_name(), getLast_name());
    }

    @Override
    public String toString() {
        return "Person{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", middle_names='" + middle_names + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String nameToString() {
        if ( Strings.isNullOrEmpty(middle_names)) {
            return first_name+" "+last_name;
        } else {
            return first_name+" "+middle_names+" "+last_name;
        }
    }
}
