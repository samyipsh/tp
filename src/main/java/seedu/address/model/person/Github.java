package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;



//@author samyipsh
/**
 * Represents a Person's github username in the contact book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGithub(String)}
 */
public class Github {

    public static final String MESSAGE_CONSTRAINTS =
            "Github username should only contain letters, numbers and hyphens.\n"
                    + "It cannot start or end with a hyphen or have multiple consecutive hyphens.\n"
                    + "It has a maximum length of 39 characters.";
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
    private static final String GITHUB_URL = "https://github.com/";
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
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidGithub(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    public String getUrl() {
        return GITHUB_URL + githubUsername;
    }

    /**
     * Returns if this Github is an empty Github.
     */
    public boolean isEmpty() {
        return equals(EMPTY_GITHUB);
    }

    /**
     * Returns false if either Github object is an empty Github.
     * Returns true if both Github objects have the same username.
     *
     * @param otherGithub The Github to compare this one to.
     * @return Whether the Github objects are the same.
     */
    public boolean isSameGithub(Github otherGithub) {
        if (otherGithub == null) {
            return false;
        }

        if (this.isEmpty() || otherGithub.isEmpty()) {
            return false;
        }

        if (otherGithub == this) {
            return true;
        }

        return githubUsername.equals(otherGithub.githubUsername);
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
