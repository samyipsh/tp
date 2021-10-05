package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GithubTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Github(null));
    }

    @Test
    public void constructor_invalidGithub_throwsIllegalArgumentException() {
        String invalidGithub = "";
        assertThrows(IllegalArgumentException.class, () -> new Github(invalidGithub));
    }

    @Test
    public void isValidGithub() {
        // null github
        assertThrows(NullPointerException.class, () -> Github.isValidGithub(null));

        // blank github
        assertFalse(Github.isValidGithub("")); // empty string
        assertFalse(Github.isValidGithub(" ")); // spaces only

        // invalid parts
        assertFalse(Github.isValidGithub("a&s")); // invalid char (not alphanumeric)
        assertFalse(Github.isValidGithub("-samyipsh")); // invalid start char
        assertFalse(Github.isValidGithub("samyipsh-")); // invalid end char


        assertFalse(Github.isValidGithub(" samyipsh")); // leading space
        assertFalse(Github.isValidGithub("samyipsh ")); // trailing space
        assertFalse(Github.isValidGithub("samyip--sh")); // consecutive -- not allowed
        assertFalse(Github.isValidGithub("samyipshsamyipshsamyipshsamyipshsamyipsh")); // more than max of 39 char

        // valid github
        assertTrue(Github.isValidGithub("alicio")); // alphabets only
        assertTrue(Github.isValidGithub("ALICIO")); // caps only
        assertTrue(Github.isValidGithub("123456")); // numbers only
        assertTrue(Github.isValidGithub("alice-io")); // - within username
        assertTrue(Github.isValidGithub("Alice-io")); // mixture of alphanumeric and hyphen
        assertTrue(Github.isValidGithub("samyipshsamyipshsamyipshsamyipshsamyips")); // long username of 39 char
    }
}
