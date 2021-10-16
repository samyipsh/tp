package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Tag tag = new Tag("tag");

    @Test
    public void execute_unfilteredList_success() {
        Tag tagToAdd = new Tag(VALID_TAG_HUSBAND);
        Person thirdPerson = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(thirdPerson);
        Person taggedPerson = personInList.withTags(VALID_TAG_HUSBAND).build();

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_THIRD_PERSON, tagToAdd);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(thirdPerson, taggedPerson);

        String expectedMessage = String.format(AddTagCommand.MESSAGE_TAG_PERSON_SUCCESS, taggedPerson);

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        showPersonAtIndex(model, indexLastPerson);

        Tag tagToAdd = new Tag(VALID_TAG_HUSBAND);
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person taggedPerson = personInList.withTags(VALID_TAG_HUSBAND).build();

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tagToAdd);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, taggedPerson);

        String expectedMessage = String.format(AddTagCommand.MESSAGE_TAG_PERSON_SUCCESS, taggedPerson);

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddTagCommand addTagCommand = new AddTagCommand(outOfBoundIndex, tag);

        assertCommandFailure(addTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddTagCommand addTagCommand = new AddTagCommand(outOfBoundIndex, tag);

        assertCommandFailure(addTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddTagCommand standardCommand = new AddTagCommand(INDEX_FIRST_PERSON, tag);

        // same values -> returns true
        AddTagCommand commandWithSameValues = new AddTagCommand(INDEX_FIRST_PERSON, tag);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddTagCommand(INDEX_SECOND_PERSON, tag)));

        // different tags -> returns false
        assertFalse(standardCommand.equals(new AddTagCommand(INDEX_FIRST_PERSON, new Tag("gat"))));
    }
}
