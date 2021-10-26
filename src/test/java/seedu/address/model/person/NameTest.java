package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void isSameNameTest() {
        Name firstName = new Name("peter jack");
        Name similarFirstName = new Name("peter jack");
        Name spacedFirstName = new Name("peter   jack   ");
        Name cappedFirstName = new Name ("Peter Jack");
        Name spacedCappedFirstName = new Name("Peter    Jack  ");
        Name secondName = new Name("peter the 2nd");

        // null name
        assertFalse(firstName.isSameName(null));

        // different name
        assertFalse(firstName.isSameName(secondName));

        // similar name
        assertTrue(firstName.isSameName(firstName));
        assertTrue(firstName.isSameName(similarFirstName));
        assertTrue(firstName.isSameName(spacedFirstName));
        assertTrue(spacedFirstName.isSameName(firstName));
        assertTrue(firstName.isSameName(cappedFirstName));
        assertTrue(cappedFirstName.isSameName(firstName));
        assertTrue(firstName.isSameName(spacedCappedFirstName));
        assertTrue(spacedCappedFirstName.isSameName(firstName));
    }
}
