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

    private final ContactBook addressBook = new ContactBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ContactBook newData = getTypicalContactBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        ContactBookStub newData = new ContactBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void hasPerson_personWithNoSameIdentityFieldsInAddressBook_returnsFalse() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_AMY)
                .withGithub(VALID_GITHUB_AMY).withLinkedIn(VALID_LINKEDIN_AMY).withEmail(VALID_EMAIL_AMY).build();
        assertFalse(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void hasPersonExcludingOtherPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPersonExcludingOtherPerson(null, ALICE));
        assertThrows(NullPointerException.class, () -> addressBook.hasPersonExcludingOtherPerson(ALICE, null));
    }

    @Test
    public void hasPersonExcludingOtherPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPersonExcludingOtherPerson(ALICE, BENSON));
    }

    @Test
    public void hasPersonExcludingOtherPerson_personInAddressBook_returnsFalse() {
        addressBook.addPerson(ALICE);
        assertFalse(addressBook.hasPersonExcludingOtherPerson(ALICE, ALICE));
    }

    @Test
    public void hasPersonExcludingOtherPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPersonExcludingOtherPerson(editedAlice, BENSON));
    }

    @Test
    public void hasPersonExcludingOtherPerson_personWithSameIdentityFieldsInAddressBook_returnsFalse() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertFalse(addressBook.hasPersonExcludingOtherPerson(editedAlice, ALICE));
    }

    @Test
    public void hasPersonExcludingOtherPerson_personWithNoSameIdentityFieldsInAddressBook_returnsFalse() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_AMY)
                .withGithub(VALID_GITHUB_AMY).withLinkedIn(VALID_LINKEDIN_AMY).withEmail(VALID_EMAIL_AMY).build();
        assertFalse(addressBook.hasPersonExcludingOtherPerson(editedAlice, ALICE));
    }

    @Test
    public void hasPersonExcludingOtherPerson_anotherPersonWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        addressBook.addPerson(BENSON);

        assertTrue(addressBook.hasPersonExcludingOtherPerson(BENSON, ALICE));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
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
