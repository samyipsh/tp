package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ReplaceTagCommand.MESSAGE_REPLACE_ALL_TAG_SUCCESS;
import static seedu.address.logic.commands.ReplaceTagCommand.MESSAGE_TAG_NOT_EXIST;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalContactBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.ContactBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.TagsPresentPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

class ReplaceTagCommandTest {
    private Model model = new ModelManager(getTypicalContactBook(), new UserPrefs());

    @Test
    void execute_tagPresent_success() {
        Tag friendTag = new Tag("friends");
        Tag enemyTag = new Tag("enemy");
        TagsPresentPredicate friendTagPredicate = new TagsPresentPredicate(Arrays.asList("friends"));

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPerson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person forthPerson = model.getFilteredPersonList().get(Index.fromOneBased(4).getZeroBased());


        Person firstDeletedTagPerson = new PersonBuilder().withName("Alice Pauline")
                .withEmail("alice@example.com")
                .withPhone("94351253")
                .withGithub("aliceio")
                .withLinkedIn("https://www.linkedin.com/in/alice/")
                .withDetail("Y1 CS").withTags("enemy").build();

        Person secondDeletedTagPerson = new PersonBuilder().withName("Benson Meier")
                .withEmail("johnd@example.com").withPhone("98765432")
                .withGithub("bensonio").withLinkedIn("https://www.linkedin.com/in/benson/")
                .withDetail("Y2 CS").withTags("owesMoney", "enemy").build();

        Person forthDeletedTagPerson = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
                .withEmail("cornelia@example.com").withGithub("danielio")
                .withLinkedIn("https://www.linkedin.com/in/danya/").withTags("enemy").build();

        ReplaceTagCommand replaceTagCommand = new ReplaceTagCommand(friendTag, enemyTag, friendTagPredicate);

        Model expectedModel = new ModelManager(new ContactBook(model.getContactBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, firstDeletedTagPerson);
        expectedModel.setPerson(secondPerson, secondDeletedTagPerson);
        expectedModel.setPerson(forthPerson, forthDeletedTagPerson);

        String expectedMessage = String.format(MESSAGE_REPLACE_ALL_TAG_SUCCESS, friendTag, enemyTag);

        assertCommandSuccess(replaceTagCommand, model, expectedMessage, expectedModel);

    }

    @Test
    void execute_tagNotFound_failure() {
        Tag clownTag = new Tag("clown");
        Tag enemyTag = new Tag("enemy");
        TagsPresentPredicate clownTagPredicate = new TagsPresentPredicate(Arrays.asList("clown"));

        ReplaceTagCommand replaceTagCommand = new ReplaceTagCommand(clownTag, enemyTag, clownTagPredicate);

        assertCommandFailure(replaceTagCommand, model,
                String.format(MESSAGE_TAG_NOT_EXIST, clownTag));
    }

    @Test
    void testEquals() {
        Tag friendTag = new Tag("friend");
        Tag enemyTag = new Tag("enemy");
        Tag clownTag = new Tag("clown");

        TagsPresentPredicate friendTagPredicate = new TagsPresentPredicate(Arrays.asList("friend"));
        TagsPresentPredicate enemyTagPredicate = new TagsPresentPredicate(Arrays.asList("enemy"));

        final ReplaceTagCommand standardCommand = new ReplaceTagCommand(friendTag, clownTag, friendTagPredicate);

        // same values -> returns true
        ReplaceTagCommand standardCommandCopy = new ReplaceTagCommand(friendTag, clownTag, friendTagPredicate);
        assertTrue(standardCommand.equals(standardCommandCopy));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different tags -> returns false
        ReplaceTagCommand differentTagCommand = new ReplaceTagCommand(enemyTag, clownTag, enemyTagPredicate);
        assertFalse(standardCommand.equals(differentTagCommand));

    }

}
