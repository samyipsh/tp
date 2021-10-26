package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;



public class LinkedInTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LinkedIn(null));
    }

    @Test
    public void constructor_invalidLinkedIn_throwsIllegalArgumentException() {
        String invalidLinkedIn = "www.link.in.com/hello";
        assertThrows(IllegalArgumentException.class, () -> new LinkedIn(invalidLinkedIn));
    }

    @Test
    public void isValidLinkedIn() {
        // null email
        assertThrows(NullPointerException.class, () -> LinkedIn.isValidLinkedIn(null));

        // missing parts
        assertFalse(LinkedIn.isValidLinkedIn("linkedin.com")); // missing local part
        assertFalse(LinkedIn.isValidLinkedIn("peterjackexample.com")); // missing '@' symbol
        assertFalse(LinkedIn.isValidLinkedIn("linkedin/12345")); // missing domain name


        // valid LinkedIn
        assertTrue(LinkedIn.isValidLinkedIn("https://ca.linkedin.com/in/winston-cahya/")); // period in local part
        assertTrue(LinkedIn.isValidLinkedIn("https://www.linkedin.com/in/winston")); // '+' symbol in local part
        assertTrue(LinkedIn.isValidLinkedIn("https://linkedin.com/in/winston-cahya/"));
    }

    @Test
    public void isEmptyTest() {
        assertTrue(LinkedIn.getEmptyLinkedin().isEmpty());
        assertFalse(new LinkedIn("https://ca.linkedin.com/in/winston-cahya/").isEmpty());
    }

    @Test
    public void isSameLinkedInTest() {
        LinkedIn emptyLinkedIn = LinkedIn.getEmptyLinkedin();
        LinkedIn otherEmptyLinkedIn = LinkedIn.getEmptyLinkedin();
        LinkedIn firstLinkedIn = new LinkedIn("https://ca.linkedin.com/in/winston-cahya/");
        LinkedIn similarFirstLinkedIn = new LinkedIn("https://ca.linkedin.com/in/winston-cahya/");
        LinkedIn secondLinkedIn = new LinkedIn("https://www.linkedin.com/in/winston");

        // null LinkedIn
        assertFalse(firstLinkedIn.isSameLinkedIn(null));

        // empty LinkedIn - not the same as any LinkedIn
        assertFalse(emptyLinkedIn.isSameLinkedIn(firstLinkedIn));
        assertFalse(emptyLinkedIn.isSameLinkedIn(otherEmptyLinkedIn));
        assertFalse(emptyLinkedIn.isSameLinkedIn(emptyLinkedIn));
        assertFalse(otherEmptyLinkedIn.isSameLinkedIn(emptyLinkedIn));

        // different LinkedIn
        assertFalse(firstLinkedIn.isSameLinkedIn(secondLinkedIn));
        assertFalse(firstLinkedIn.isSameLinkedIn(emptyLinkedIn));

        // similar LinkedIn
        assertTrue(firstLinkedIn.isSameLinkedIn(firstLinkedIn));
        assertTrue(firstLinkedIn.isSameLinkedIn(similarFirstLinkedIn));
    }
}
