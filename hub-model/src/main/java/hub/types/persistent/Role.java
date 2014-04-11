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
public class Role implements SingleStringValue {

    @NotEmpty
    private String rolename;

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public Role() {
    }


    @Override
    public String getValue() {
        return rolename;
    }

    @Override
    public void setValue(String value) {
        this.rolename = value;
    }



    public int hashCode() {
        return Objects.hashCode(getValue());
    }

    @Override
    public String toString() {
        return getValue();
    }
}
