package hub.types.persistent;

import java.util.Objects;

import org.hibernate.validator.constraints.NotEmpty;

import things.model.SingleStringValue;
import things.model.types.attributes.Subordinate;
import things.model.types.attributes.UniqueKeyInOtherThings;

/**
 * Project: hub
 * <p/>
 * Written by: Markus Binsteiner
 * Date: 12/03/14
 * Time: 1:51 PM
 */
@UniqueKeyInOtherThings(unique = false)
@Subordinate(parentClass = Person.class)
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
