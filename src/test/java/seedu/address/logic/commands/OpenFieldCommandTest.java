package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.OpenFieldCommand.MESSAGE_UNSUPPORTED_FIELD;
import static seedu.address.testutil.TypicalIndexes.INDEX_EMPTY_FIELD_PERSON;
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

public class OpenFieldCommandTest {
    private Model model = new ModelManager(getTypicalContactBook(), new UserPrefs());
    private final Predicate<Person> predicateNoPersons = unused -> false;

    private final String githubOpenFieldSuccess = String.format(OpenFieldCommand.MESSAGE_OPEN_FIELD_SUCCESS, "github");
    private final String linkedInOpenFieldSuccess =
            String.format(OpenFieldCommand.MESSAGE_OPEN_FIELD_SUCCESS, "linkedin");
    private final String unsupportedFieldFailure = String.format(MESSAGE_UNSUPPORTED_FIELD, "somefield");

    @Test
    public void execute_validFieldAndOneIndex_modelUnchangedAndSuccess() {
        List<Index> secondIndex = new ArrayList<>();

        secondIndex.add(INDEX_SECOND_PERSON); //has both linkedin and github field

        Model expectedModel = new ModelManager(new ContactBook(model.getContactBook()), new UserPrefs());
        OpenFieldCommand openGithubFieldCommand = new OpenFieldCommand(secondIndex, "github");
        OpenFieldCommand openLinkedInFieldCommand = new OpenFieldCommand(secondIndex, "linkedin");

        assertCommandSuccess(openGithubFieldCommand, model, githubOpenFieldSuccess, expectedModel);
        assertCommandSuccess(openLinkedInFieldCommand, model, linkedInOpenFieldSuccess, expectedModel);
    }

    @Test
    public void execute_validFieldAndMultipleIndex_modelUnchangedAndSuccess() {
        List<Index> firstAndSecondIndex = new ArrayList<>();

        //has both linkedin and github field
        firstAndSecondIndex.add(INDEX_FIRST_PERSON);
        firstAndSecondIndex.add(INDEX_SECOND_PERSON);

        Model expectedModel = new ModelManager(new ContactBook(model.getContactBook()), new UserPrefs());
        OpenFieldCommand openGithubFieldCommand = new OpenFieldCommand(firstAndSecondIndex, "github");
        OpenFieldCommand openLinkedInFieldCommand = new OpenFieldCommand(firstAndSecondIndex, "linkedin");

        assertCommandSuccess(openGithubFieldCommand, model, githubOpenFieldSuccess, expectedModel);
        assertCommandSuccess(openLinkedInFieldCommand, model, linkedInOpenFieldSuccess, expectedModel);
    }

    @Test
    public void execute_emptyFieldAndMultipleIndex_modelUnchangedAndSuccess() {
        List<Index> index = new ArrayList<>();

        //missing LinkedIn field
        index.add(INDEX_EMPTY_FIELD_PERSON);

        Model expectedModel = new ModelManager(new ContactBook(model.getContactBook()), new UserPrefs());
        OpenFieldCommand openGithubFieldCommand = new OpenFieldCommand(index, "github");
        OpenFieldCommand openLinkedInFieldCommand = new OpenFieldCommand(index, "linkedin");

        assertCommandSuccess(openGithubFieldCommand, model, githubOpenFieldSuccess, expectedModel);
        assertCommandSuccess(openLinkedInFieldCommand, model, linkedInOpenFieldSuccess, expectedModel);
    }

    //TODO uncomment comment when data changes
    @Test
    public void execute_invalidField_failure() {
        List<Index> secondIndex = new ArrayList<>();

        secondIndex.add(INDEX_SECOND_PERSON); //has both linkedin and github field

        Model expectedModel = new ModelManager(new ContactBook(model.getContactBook()), new UserPrefs());
        OpenFieldCommand openGithubFieldCommand = new OpenFieldCommand(secondIndex, "someField");

        //MODIFY when new list data from james is filled
        //assertCommandFailure(openGithubFieldCommand, model, unsupportedFieldFailure);
    }

    @Test
    public void execute_emptyList_failure() {
        //        model.updateFilteredPersonList(predicateNoPersons);
        //        Index firstIndex = INDEX_FIRST_PERSON;
        //        // ensures that outOfBoundIndex is still in bounds of address book list
        //        assertTrue(firstIndex.getZeroBased() < model.getContactBook().getPersonList().size());
        //
        //        List<Index> invalidIndex = new ArrayList<>();
        //        invalidIndex.add(firstIndex);
        //
        //        OpenFieldCommand openGithubFieldCommand = new OpenFieldCommand(invalidIndex, "github");
        //        assertCommandFailure(openGithubFieldCommand, model,
        //                String.format(MESSAGE_NO_DISPLAYED_PERSONS));
    }
}
