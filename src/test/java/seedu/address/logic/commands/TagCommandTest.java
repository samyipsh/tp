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
import static seedu.address.testutil.TypicalPersons.getTypicalContactBook;

import java.util.ArrayList;
import java.util.List;
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

public class TagCommandTest {
    private Model model = new ModelManager(getTypicalContactBook(), new UserPrefs());
    private Tag tag = new Tag("tag");
    private final Predicate<Person> predicateNoPersons = unused -> false;


    @Test
    public void execute_unfilteredList_success() {
        Tag tagToAdd = new Tag(VALID_TAG_HUSBAND);
        List<Index> thirdIndex = new ArrayList<>();
        thirdIndex.add(INDEX_THIRD_PERSON);

        Person thirdPerson = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(thirdPerson);
        Person taggedPerson = personInList.withTags(VALID_TAG_HUSBAND).build();

        TagCommand tagCommand = new TagCommand(thirdIndex, tagToAdd);

        Model expectedModel = new ModelManager(new ContactBook(model.getContactBook()), new UserPrefs());
        expectedModel.setPerson(thirdPerson, taggedPerson);

        String expectedMessage = TagCommand.MESSAGE_TAG_PERSON_SUCCESS;

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        showPersonAtIndex(model, indexLastPerson);

        Tag tagToAdd = new Tag(VALID_TAG_HUSBAND);
        List<Index> firstIndex = new ArrayList<>();
        firstIndex.add(INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person taggedPerson = personInList.withTags(VALID_TAG_HUSBAND).build();

        TagCommand tagCommand = new TagCommand(firstIndex, tagToAdd);

        Model expectedModel = new ModelManager(new ContactBook(model.getContactBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, taggedPerson);
        showPersonAtIndex(expectedModel, indexLastPerson);

        String expectedMessage = TagCommand.MESSAGE_TAG_PERSON_SUCCESS;

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        int modelSize = model.getFilteredPersonList().size();

        Index outOfBoundIndex = Index.fromOneBased(modelSize + 1);
        List<Index> invalidIndex = new ArrayList<>();
        invalidIndex.add(outOfBoundIndex);

        TagCommand tagCommand = new TagCommand(invalidIndex, tag);

        assertCommandFailure(tagCommand, model,
                String.format(TagCommand.MESSAGE_OUT_OF_BOUNDS_INDEX_DISPLAYED, modelSize + 1, modelSize));
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContactBook().getPersonList().size());

        List<Index> invalidIndex = new ArrayList<>();
        invalidIndex.add(outOfBoundIndex);

        TagCommand tagCommand = new TagCommand(invalidIndex, tag);

        assertCommandFailure(tagCommand, model,
                String.format(TagCommand.MESSAGE_OUT_OF_BOUNDS_INDEX_DISPLAYED, 2, 1));
    }

    @Test
    public void execute_emptyList_failure() {
        model.updateFilteredPersonList(predicateNoPersons);
        Index outOfBoundIndex = INDEX_FIRST_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContactBook().getPersonList().size());

        List<Index> invalidIndex = new ArrayList<>();
        invalidIndex.add(outOfBoundIndex);

        TagCommand tagCommand = new TagCommand(invalidIndex, tag);

        assertCommandFailure(tagCommand, model,
                TagCommand.MESSAGE_NO_DISPLAYED_PERSONS);
    }

    @Test
    public void equals() {
        List<Index> firstIndex = new ArrayList<>();
        List<Index> secondIndex = new ArrayList<>();

        firstIndex.add(INDEX_FIRST_PERSON);
        secondIndex.add(INDEX_SECOND_PERSON);

        final TagCommand standardCommand = new TagCommand(firstIndex, tag);

        // same values -> returns true
        TagCommand commandWithSameValues = new TagCommand(firstIndex, tag);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new TagCommand(secondIndex, tag)));

        // different tags -> returns false
        assertFalse(standardCommand.equals(new TagCommand(firstIndex, new Tag("gat"))));
    }
}
