package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.UntagCommand.MESSAGE_UNTAG_PERSON_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
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

class UntagCommandTest {
    private Model model = new ModelManager(getTypicalContactBook(), new UserPrefs());
    private Tag tag = new Tag("tag");
    private final Predicate<Person> predicateNoPersons = unused -> false;


    @Test
    void execute_unfilteredList_success() {
        Tag untag = new Tag("friends");
        List<Index> secondIndex = new ArrayList<>();
        secondIndex.add(INDEX_SECOND_PERSON);

        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        Person untaggedPerson = new PersonBuilder().withName("Benson Meier")
                .withEmail("johnd@example.com").withPhone("98765432")
                .withGithub("bensonio").withLinkedIn("https://www.linkedin.com/in/benson/")
                .withDetail("Y2 CS").withTags("owesMoney").build();

        UntagCommand untagCommand = new UntagCommand(secondIndex, untag);

        Model expectedModel = new ModelManager(new ContactBook(model.getContactBook()), new UserPrefs());
        expectedModel.setPerson(secondPerson, untaggedPerson);

        String expectedMessage = String.format(MESSAGE_UNTAG_PERSON_SUCCESS, untag);

        assertCommandSuccess(untagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Index indexSecondPerson = Index.fromOneBased(2);
        showPersonAtIndex(model, indexSecondPerson);

        Tag untag = new Tag("owesMoney");
        List<Index> firstIndex = new ArrayList<>();
        firstIndex.add(INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Person untaggedPerson = new PersonBuilder().withName("Benson Meier")
                .withEmail("johnd@example.com").withPhone("98765432")
                .withGithub("bensonio").withLinkedIn("https://www.linkedin.com/in/benson/")
                .withDetail("Y2 CS").withTags("friends").build();

        UntagCommand untagCommand = new UntagCommand(firstIndex, untag);

        Model expectedModel = new ModelManager(new ContactBook(model.getContactBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, untaggedPerson);
        showPersonAtIndex(expectedModel, indexSecondPerson);

        String expectedMessage = String.format(MESSAGE_UNTAG_PERSON_SUCCESS, untag);

        assertCommandSuccess(untagCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        int modelSize = model.getFilteredPersonList().size();

        Index outOfBoundIndex = Index.fromOneBased(modelSize + 1);
        List<Index> invalidIndex = new ArrayList<>();
        invalidIndex.add(outOfBoundIndex);

        UntagCommand untagCommand = new UntagCommand(invalidIndex, tag);

        assertCommandFailure(untagCommand, model,
                String.format(UntagCommand.MESSAGE_OUT_OF_BOUNDS_INDEX_DISPLAYED, modelSize + 1, modelSize));
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContactBook().getPersonList().size());

        List<Index> invalidIndex = new ArrayList<>();
        invalidIndex.add(outOfBoundIndex);

        UntagCommand untagCommand = new UntagCommand(invalidIndex, tag);

        assertCommandFailure(untagCommand, model,
                String.format(UntagCommand.MESSAGE_OUT_OF_BOUNDS_INDEX_DISPLAYED, 2, 1));
    }

    @Test
    public void execute_emptyList_failure() {
        model.updateFilteredPersonList(predicateNoPersons);
        Index outOfBoundIndex = INDEX_FIRST_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContactBook().getPersonList().size());

        List<Index> invalidIndex = new ArrayList<>();
        invalidIndex.add(outOfBoundIndex);

        UntagCommand untagCommand = new UntagCommand(invalidIndex, tag);

        assertCommandFailure(untagCommand, model,
                UntagCommand.MESSAGE_NO_DISPLAYED_PERSONS);
    }

    @Test
    public void execute_tagNotFound_failure() {
        Index indexSecondPerson = Index.fromOneBased(2);
        List<Index> secondIndex = new ArrayList<>();
        secondIndex.add(INDEX_SECOND_PERSON);

        UntagCommand untagCommand = new UntagCommand(secondIndex, tag);
        assertCommandFailure(untagCommand, model,
                String.format(UntagCommand.MESSAGE_TAG_NOT_EXIST, tag));


    }

    @Test
    void testEquals() {
        List<Index> firstIndex = new ArrayList<>();
        List<Index> secondIndex = new ArrayList<>();

        firstIndex.add(INDEX_FIRST_PERSON);
        secondIndex.add(INDEX_SECOND_PERSON);

        final UntagCommand standardCommand = new UntagCommand(firstIndex, tag);

        // same values -> returns true
        UntagCommand commandWithSameValues = new UntagCommand(firstIndex, tag);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new UntagCommand(secondIndex, tag)));

        // different tags -> returns false
        assertFalse(standardCommand.equals(new UntagCommand(firstIndex, new Tag("gat"))));
    }

}
