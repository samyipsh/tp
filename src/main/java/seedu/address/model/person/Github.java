package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;



//@author samyipsh
/**
 * Represents a Person's github username in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGithub(String)}
 */
public class Github {

    public static final String MESSAGE_CONSTRAINTS =
            "Github username should only contain alphanumeric characters and hyphens"
                    + ", it cannot have multiple consecutive hyphens"
                    + ", cannot start or end with a hyphen"
                    + ", and has maximum 39 char";
    /* reused from https://github.com/shinnn/github-username-regex
     *
     * Github username may only contain alphanumeric characters or hyphens.
     * Github username cannot have multiple consecutive hyphens.
     * Github username cannot begin or end with a hyphen.
     * Maximum is 39 characters.
     */
    public static final String VALIDATION_REGEX =
            "(?i)^[a-z\\d](?:[a-z\\d]|-(?=[a-z\\d])){0,38}$";

    private static final Github EMPTY_GITHUB = new Github();
    public final String githubUsername;

    /**
     * Constructs a {@code Name}.
     *
     * @param githubUsername A valid name.
     */
    public Github(String githubUsername) {
        requireNonNull(githubUsername);
        checkArgument(isValidGithub(githubUsername), MESSAGE_CONSTRAINTS);
        this.githubUsername = githubUsername;
    }

    /**
     * Constructs an Empty Github.
     */
    private Github() {
        githubUsername = "-";
    }

    /**
     * Get an Empty Github.
     *
     * @return EMPTY_GITHUB.
     */
    public static Github getEmptyGithub() {
        return EMPTY_GITHUB;
    }

    /**
     * Check whether it is an empty github
     *
     * @return boolean
     */
    public boolean isEmptyGithub() {
        return this.githubUsername.equals("-");
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidGithub(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return githubUsername;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Github // instanceof handles nulls
                && githubUsername.equals(((Github) other).githubUsername)); // state check
    }

    @Override
    public int hashCode() {
        return githubUsername.hashCode();
    }

}
