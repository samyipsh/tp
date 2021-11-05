package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CLARA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_WIFE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.ContactBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ContactBookBuilder;
import seedu.address.testutil.PersonBuilder;

class ShowTagsCommandTest {

    @Test
    void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        String expectedMessage = ShowTagsCommand.MESSAGE_SUCCESS + "\n";

        assertCommandSuccess(new ShowTagsCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    void execute_noTags_success() {
        final Person amy = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY).build();

        final Person bob = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();

        ContactBook addressBook = new ContactBookBuilder().withPerson(amy).withPerson(bob).build();
        UserPrefs userPrefs = new UserPrefs();

        Model model = new ModelManager(addressBook, userPrefs);
        Model expectedModel = new ModelManager(addressBook, userPrefs);

        String expectedMessage = ShowTagsCommand.MESSAGE_SUCCESS + "\n";

        assertCommandSuccess(new ShowTagsCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    void execute_noDuplicateTags_success() {
        Person person = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withTags(VALID_TAG_FRIEND).build();

        ContactBook addressBook = new ContactBookBuilder().withPerson(person).build();
        UserPrefs userPrefs = new UserPrefs();

        Model model = new ModelManager(addressBook, userPrefs);
        Model expectedModel = new ModelManager(addressBook, userPrefs);

        String expectedMessage = ShowTagsCommand.MESSAGE_SUCCESS + "\n"
                + new Tag(VALID_TAG_FRIEND);

        assertCommandSuccess(new ShowTagsCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    void execute_duplicateTags_success() {
        final Person amy = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withTags(VALID_TAG_FRIEND).build();

        final Person bob = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_FRIEND).build();

        ContactBook addressBook = new ContactBookBuilder().withPerson(amy).withPerson(bob).build();
        UserPrefs userPrefs = new UserPrefs();

        Model model = new ModelManager(addressBook, userPrefs);
        Model expectedModel = new ModelManager(addressBook, userPrefs);

        String expectedMessage = ShowTagsCommand.MESSAGE_SUCCESS + "\n"
                + new Tag(VALID_TAG_FRIEND);

        assertCommandSuccess(new ShowTagsCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    void execute_multiplePersons_success() {
        final Person amy = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withTags(VALID_TAG_FRIEND).build();

        final Person bob = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();

        final Person clara = new PersonBuilder().withName(VALID_NAME_CLARA).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_WIFE).build();

        ContactBook addressBook = new ContactBookBuilder().withPerson(amy).withPerson(bob)
                .withPerson(clara).build();
        UserPrefs userPrefs = new UserPrefs();

        Model model = new ModelManager(addressBook, userPrefs);
        Model expectedModel = new ModelManager(addressBook, userPrefs);

        String expectedMessage = ShowTagsCommand.MESSAGE_SUCCESS + "\n"
                + new Tag(VALID_TAG_FRIEND) + " "
                + new Tag(VALID_TAG_HUSBAND) + " "
                + new Tag(VALID_TAG_WIFE);

        assertCommandSuccess(new ShowTagsCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    void execute_filteredPersons_success() {
        final Person amy = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withTags(VALID_TAG_FRIEND).build();

        final Person bob = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();

        final Person clara = new PersonBuilder().withName(VALID_NAME_CLARA).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_WIFE).build();

        ContactBook addressBook = new ContactBookBuilder().withPerson(amy).withPerson(bob)
                .withPerson(clara).build();
        UserPrefs userPrefs = new UserPrefs();

        Model model = new ModelManager(addressBook, userPrefs);
        Model expectedModel = new ModelManager(addressBook, userPrefs);

        model.updateFilteredPersonList(person -> person.getName().equals(VALID_NAME_AMY));
        expectedModel.updateFilteredPersonList(person -> person.getName().equals(VALID_NAME_AMY));

        String expectedMessage = ShowTagsCommand.MESSAGE_SUCCESS + "\n"
                + new Tag(VALID_TAG_FRIEND) + " "
                + new Tag(VALID_TAG_HUSBAND) + " "
                + new Tag(VALID_TAG_WIFE);

        assertCommandSuccess(new ShowTagsCommand(), model, expectedMessage, expectedModel);
    }
}
