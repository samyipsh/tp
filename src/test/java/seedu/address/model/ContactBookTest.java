package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINKEDIN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalContactBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class ContactBookTest {

    private final ContactBook contactBook = new ContactBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), contactBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyContactBook_replacesData() {
        ContactBook newData = getTypicalContactBook();
        contactBook.resetData(newData);
        assertEquals(newData, contactBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        ContactBookStub newData = new ContactBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> contactBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInContactBook_returnsFalse() {
        assertFalse(contactBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInContactBook_returnsTrue() {
        contactBook.addPerson(ALICE);
        assertTrue(contactBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInContactBook_returnsTrue() {
        contactBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(contactBook.hasPerson(editedAlice));
    }

    @Test
    public void hasPerson_personWithNoSameIdentityFieldsInContactBook_returnsFalse() {
        contactBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_AMY)
                .withGithub(VALID_GITHUB_AMY).withLinkedIn(VALID_LINKEDIN_AMY).withEmail(VALID_EMAIL_AMY).build();
        assertFalse(contactBook.hasPerson(editedAlice));
    }

    @Test
    public void hasPersonExcludingOtherPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactBook.hasPersonExcludingOtherPerson(null, ALICE));
        assertThrows(NullPointerException.class, () -> contactBook.hasPersonExcludingOtherPerson(ALICE, null));
    }

    @Test
    public void hasPersonExcludingOtherPerson_personInContactBook_returnsTrue() {
        contactBook.addPerson(ALICE);
        assertTrue(contactBook.hasPersonExcludingOtherPerson(ALICE, BENSON));
    }

    @Test
    public void hasPersonExcludingOtherPerson_personInContactBook_returnsFalse() {
        contactBook.addPerson(ALICE);
        assertFalse(contactBook.hasPersonExcludingOtherPerson(ALICE, ALICE));
    }

    @Test
    public void hasPersonExcludingOtherPerson_personWithSameIdentityFieldsInContactBook_returnsTrue() {
        contactBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(contactBook.hasPersonExcludingOtherPerson(editedAlice, BENSON));
    }

    @Test
    public void hasPersonExcludingOtherPerson_personWithSameIdentityFieldsInContactBook_returnsFalse() {
        contactBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertFalse(contactBook.hasPersonExcludingOtherPerson(editedAlice, ALICE));
    }

    @Test
    public void hasPersonExcludingOtherPerson_personWithNoSameIdentityFieldsInContactBook_returnsFalse() {
        contactBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_AMY)
                .withGithub(VALID_GITHUB_AMY).withLinkedIn(VALID_LINKEDIN_AMY).withEmail(VALID_EMAIL_AMY).build();
        assertFalse(contactBook.hasPersonExcludingOtherPerson(editedAlice, ALICE));
    }

    @Test
    public void hasPersonExcludingOtherPerson_anotherPersonWithSameIdentityFieldsInContactBook_returnsTrue() {
        contactBook.addPerson(ALICE);
        contactBook.addPerson(BENSON);

        assertTrue(contactBook.hasPersonExcludingOtherPerson(BENSON, ALICE));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> contactBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyContactBook whose persons list can violate interface constraints.
     */
    private static class ContactBookStub implements ReadOnlyContactBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        ContactBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
