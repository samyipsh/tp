package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only
        assertFalse(Phone.isValidPhone("91")); // less than 3 numbers
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("124293842033123")); // long phone numbers
    }

    @Test
    public void isEmptyTest() {
        assertTrue(Phone.getEmptyPhone().isEmpty());
        assertFalse(new Phone("911").isEmpty());
    }

    @Test
    public void isSamePhoneTest() {
        Phone emptyPhone = Phone.getEmptyPhone();
        Phone otherEmptyPhone = Phone.getEmptyPhone();
        Phone firstPhone = new Phone("999");
        Phone secondPhone = new Phone("911");
        Phone sameFirstPhone = new Phone("999");

        // null phone number
        assertFalse(firstPhone.isSamePhone(null));

        // empty phone number - not the same as any phone number
        assertFalse(emptyPhone.isSamePhone(firstPhone));
        assertFalse(emptyPhone.isSamePhone(emptyPhone));
        assertFalse(emptyPhone.isSamePhone(otherEmptyPhone));
        assertFalse(otherEmptyPhone.isSamePhone(emptyPhone));

        // different phone number
        assertFalse(firstPhone.isSamePhone(secondPhone));
        assertFalse(firstPhone.isSamePhone(emptyPhone));

        // similar phone number
        assertTrue(firstPhone.isSamePhone(firstPhone));
        assertTrue(firstPhone.isSamePhone(sameFirstPhone));
    }
}
