package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class LinkedIn {
    //Regex copied from https://stackoverflow.com/questions/
    // 30256969/how-to-validate-linkedin-public-profile-url-regular-expression-in-python/30257327

    public static final String MESSAGE_CONSTRAINTS = "Not a valid linkedIn URL\n"
            + "Example: https://www.linkedin.com/in/winston-cahya/";
    private static final String VALIDATION_REGEX =
            "^(http(s?)://)?([\\w\\d\\-\\_]*\\.)?linkedin.com/[^.\\s]*$";
    private static final LinkedIn EMPTY_LINKEDIN = new LinkedIn();
    public final String value;

    /**
     * Constructs an {@code LinkedIn}.
     *
     * @param  linkedinUrl linkedIn URL.
     */
    public LinkedIn(String linkedinUrl) {
        requireNonNull(linkedinUrl);
        checkArgument(isValidLinkedIn(linkedinUrl), MESSAGE_CONSTRAINTS);
        value = linkedinUrl;
    }

    /**
     * Constructs an Empty LinkedIn.
     */
    private LinkedIn() {
        value = "-";
    }

    /**
     * Get an Empty LinkedIn.
     *
     * @return EMPTY_LINKEDIN.
     */
    public static LinkedIn getEmptyLinkedin() {
        return EMPTY_LINKEDIN;
    }


    /**
     * Returns if a given string is a valid linkedin
     */
    public static boolean isValidLinkedIn(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getUrl() {
        return value;
    }

    /**
     * Returns if this linkedin is an empty linkedin.
     */
    public boolean isEmpty() {
        return equals(EMPTY_LINKEDIN);
    }

    /**
     * Returns false if either LinkedIn object is an empty LinkedIn.
     * Returns true if both LinkedIn objects have the same LinkedIn URL.
     *
     * @param otherLinkedIn The LinkedIn to compare this one to.
     * @return Whether the LinkedIn objects are the same.
     */
    public boolean isSameLinkedIn(LinkedIn otherLinkedIn) {
        if (otherLinkedIn == null) {
            return false;
        }

        if (this.isEmpty() || otherLinkedIn.isEmpty()) {
            return false;
        }

        if (otherLinkedIn == this) {
            return true;
        }

        return value.equals(otherLinkedIn.value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkedIn // instanceof handles nulls
                && value.equals(((LinkedIn) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
