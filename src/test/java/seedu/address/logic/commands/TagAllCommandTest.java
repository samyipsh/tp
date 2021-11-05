package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalContactBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.ContactBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

class TagAllCommandTest {
    private Model model = new ModelManager(getTypicalContactBook(), new UserPrefs());
    private Tag tag = new Tag("tag");
    private final Predicate<Person> predicateNoPersons = unused -> false;

    @Test
    public void execute_filteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        showPersonAtIndex(model, indexLastPerson);

        Tag tagToAdd = new Tag(VALID_TAG_HUSBAND);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person taggedPerson = personInList.withTags(VALID_TAG_HUSBAND).build();

        TagAllCommand tagAllCommand = new TagAllCommand(tagToAdd);

        Model expectedModel = new ModelManager(new ContactBook(model.getContactBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, taggedPerson);
        showPersonAtIndex(expectedModel, indexLastPerson);

        String expectedMessage = TagAllCommand.MESSAGE_SUCCESS;

        assertCommandSuccess(tagAllCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyList_failure() {
        model.updateFilteredPersonList(predicateNoPersons);

        TagAllCommand tagAllCommand = new TagAllCommand(tag);

        assertCommandFailure(tagAllCommand, model,
                TagAllCommand.MESSAGE_NO_PERSONS_TO_TAG);
    }

    @Test
    public void equals() {
        final TagAllCommand standardCommand = new TagAllCommand(tag);

        // same values -> returns true
        TagAllCommand commandWithSameValues = new TagAllCommand(tag);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different tags -> returns false
        assertFalse(standardCommand.equals(new TagAllCommand(new Tag("gat"))));
    }
}
