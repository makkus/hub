package hub.types.persistent;

import org.hibernate.validator.constraints.NotEmpty;
import things.model.SingleStringValue;
import things.model.types.Value;
import things.model.types.attributes.Subordinate;
import things.model.types.attributes.UniqueKeyInOtherThings;

import java.util.Objects;

/**
 * A username is the identifying token for a user on a system.
 *
 * It is linked to the {@link Person} object. When adding a Username to a Person, the key that is used is
 * the name of the system where the username is used.
 *
 * A {@link Person}} object can have multiple Usernames with the same keys, since a user can have multiple
 * accounts on a system.
 */
@UniqueKeyInOtherThings(unique = false)
@Subordinate(parentClass = Person.class)
@Value(typeName = "username")
public class Username implements SingleStringValue {

    @NotEmpty
    private String value;

    public Username(String value) {
        this.value = value;
    }

    public Username() {
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;

        if (getClass().equals(obj.getClass())) {
            final Username other = (Username) obj;
            return Objects.equals(getValue(), other.getValue());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hashCode(getValue());
    }

    @Override
    public String toString() {
        return getValue();
    }

}
