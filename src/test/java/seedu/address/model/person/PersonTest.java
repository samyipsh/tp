package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DETAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINKEDIN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).withGithub(VALID_GITHUB_AMY).withLinkedIn(VALID_LINKEDIN_AMY)
                .withDetail(VALID_DETAIL_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same name, all other attributes different except LinkedIn -> returns true
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).withGithub(VALID_GITHUB_AMY).withDetail(VALID_DETAIL_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, all other attributes different except Github -> returns true
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).withLinkedIn(VALID_LINKEDIN_AMY).withDetail(VALID_DETAIL_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, all other attributes different except Email -> returns true
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND)
                .withGithub(VALID_GITHUB_AMY).withLinkedIn(VALID_LINKEDIN_AMY).withDetail(VALID_DETAIL_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, all other attributes different except Phone -> returns true
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND)
                .withGithub(VALID_GITHUB_AMY).withLinkedIn(VALID_LINKEDIN_AMY).withDetail(VALID_DETAIL_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, all other attributes empty -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmptyEmail().withEmptyPhone()
                .withEmptyGithub().withEmptyLinkedin().build();
        assertFalse(ALICE.isSamePerson(editedAlice));
        assertFalse(editedAlice.isSamePerson(ALICE));

        // same name, both of all attributes empty -> returns true
        Person emptyAlice = new PersonBuilder(ALICE).withEmptyEmail().withEmptyPhone()
                .withEmptyGithub().withEmptyLinkedin().build();
        assertTrue(emptyAlice.isSamePerson(editedAlice));
        assertTrue(editedAlice.isSamePerson(emptyAlice));


        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
