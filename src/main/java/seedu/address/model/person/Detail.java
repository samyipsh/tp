package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's detail in the contact book.
 * Guarantees: immutable;
 */
public class Detail {

    public final String value;

    /**
     * Constructs a {@code Detail}.
     *
     * @param detail A detail.
     */
    public Detail(String detail) {
        requireNonNull(detail);
        value = detail;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Detail // instanceof handles nulls
                && value.equals(((Detail) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
