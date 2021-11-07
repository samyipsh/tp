package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the contact book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    private static final Phone EMPTY_PHONE = new Phone();
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Constructs an Empty Phone.
     */
    private Phone() {
        value = "-";
    }

    public static Phone getEmptyPhone() {
        return EMPTY_PHONE;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns if this Phone is an empty Phone.
     */
    public boolean isEmpty() {
        return equals(EMPTY_PHONE);
    }

    /**
     * Returns false if either Phone object is an empty Phone.
     * Returns true if both Phone objects have the same number.
     *
     * @param otherPhone The Phone to compare this one to.
     * @return Whether the Phone objects are the same.
     */
    public boolean isSamePhone(Phone otherPhone) {
        if (otherPhone == null) {
            return false;
        }

        if (this.isEmpty() || otherPhone.isEmpty()) {
            return false;
        }

        if (otherPhone == this) {
            return true;
        }

        return value.equals(otherPhone.value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && value.equals(((Phone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
