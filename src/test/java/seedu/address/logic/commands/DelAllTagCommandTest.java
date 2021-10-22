package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DelAllTagCommand.MESSAGE_DELETE_ALL_TAG_SUCCESS;
import static seedu.address.logic.commands.DelAllTagCommand.MESSAGE_TAG_NOT_EXIST;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.TagsPresentPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

class DelAllTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_tagPresent_success() {
        Tag friendTag = new Tag("friends");
        TagsPresentPredicate friendTagPredicate = new TagsPresentPredicate(Arrays.asList("friends"));

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person forthPerson = model.getFilteredPersonList().get(Index.fromOneBased(4).getZeroBased());

        Person firstDeletedTagPerson = new PersonBuilder().withName("Alice Pauline")
                .withEmail("alice@example.com")
                .withPhone("94351253")
                .withGithub("aliceio")
                .withLinkedIn("https://www.linkedin.com/in/alice/")
                .withDetail("Y1 CS").build();

        Person secondDeletedTagPerson = new PersonBuilder().withName("Benson Meier")
                .withEmail("johnd@example.com").withPhone("98765432")
                .withGithub("bensonio").withLinkedIn("https://www.linkedin.com/in/benson/")
                .withDetail("Y2 CS").withTags("owesMoney").build();

        Person forthDeletedTagPerson = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
                .withEmail("cornelia@example.com").withGithub("danielio")
                .withLinkedIn("https://www.linkedin.com/in/danya/").build();

        DelAllTagCommand delAllTagCommand = new DelAllTagCommand(friendTag, friendTagPredicate);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, firstDeletedTagPerson);
        expectedModel.setPerson(secondPerson, secondDeletedTagPerson);
        expectedModel.setPerson(forthPerson, forthDeletedTagPerson);

        String expectedMessage = String.format(MESSAGE_DELETE_ALL_TAG_SUCCESS, friendTag);

        assertCommandSuccess(delAllTagCommand, model, expectedMessage, expectedModel);

    }

    @Test
    void execute_tagNotFound_failure() {
        Tag enemyTag = new Tag("enemy");
        TagsPresentPredicate friendTagPredicate = new TagsPresentPredicate(Arrays.asList("enemy"));

        DelAllTagCommand delAllTagCommand = new DelAllTagCommand(enemyTag, friendTagPredicate);

        assertCommandFailure(delAllTagCommand, model,
                String.format(MESSAGE_TAG_NOT_EXIST, enemyTag));
    }

    @Test
    void testEquals() {
        Tag friendTag = new Tag("friend");
        Tag enemyTag = new Tag("enemy");

        TagsPresentPredicate friendTagPredicate = new TagsPresentPredicate(Arrays.asList("friend"));
        TagsPresentPredicate enemyTagPredicate = new TagsPresentPredicate(Arrays.asList("enemy"));

        final DelAllTagCommand standardCommand = new DelAllTagCommand(friendTag, friendTagPredicate);

        // same values -> returns true
        DelAllTagCommand standardCommandCopy = new DelAllTagCommand(friendTag, friendTagPredicate);
        assertTrue(standardCommand.equals(standardCommandCopy));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different tags -> returns false
        DelAllTagCommand differentTagCommand = new DelAllTagCommand(enemyTag, enemyTagPredicate);
        assertFalse(standardCommand.equals(differentTagCommand));

    }

}
