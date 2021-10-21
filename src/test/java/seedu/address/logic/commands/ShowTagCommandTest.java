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

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;

class ShowTagCommandTest {

    @Test
    void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        String expectedMessage = ShowTagCommand.MESSAGE_SUCCESS + "\n";

        assertCommandSuccess(new ShowTagCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    void execute_noDuplicateTags_success() {
        Person person = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withTags(VALID_TAG_FRIEND).build();

        AddressBook addressBook = new AddressBookBuilder().withPerson(person).build();
        UserPrefs userPrefs = new UserPrefs();

        Model model = new ModelManager(addressBook, userPrefs);
        Model expectedModel = new ModelManager(addressBook, userPrefs);

        String expectedMessage = ShowTagCommand.MESSAGE_SUCCESS + "\n"
                + new Tag(VALID_TAG_FRIEND);

        assertCommandSuccess(new ShowTagCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    void execute_duplicateTags_success() {
        final Person amy = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withTags(VALID_TAG_FRIEND).build();

        final Person bob = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_FRIEND).build();

        AddressBook addressBook = new AddressBookBuilder().withPerson(amy).withPerson(bob).build();
        UserPrefs userPrefs = new UserPrefs();

        Model model = new ModelManager(addressBook, userPrefs);
        Model expectedModel = new ModelManager(addressBook, userPrefs);

        String expectedMessage = ShowTagCommand.MESSAGE_SUCCESS + "\n"
                + new Tag(VALID_TAG_FRIEND);

        assertCommandSuccess(new ShowTagCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    void execute_multiplePersons_success() {
        final Person amy = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withTags(VALID_TAG_FRIEND).build();

        final Person bob = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();

        final Person clara = new PersonBuilder().withName(VALID_NAME_CLARA).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_WIFE).build();

        AddressBook addressBook = new AddressBookBuilder().withPerson(amy).withPerson(bob)
                .withPerson(clara).build();
        UserPrefs userPrefs = new UserPrefs();

        Model model = new ModelManager(addressBook, userPrefs);
        Model expectedModel = new ModelManager(addressBook, userPrefs);

        String expectedMessage = ShowTagCommand.MESSAGE_SUCCESS + "\n"
                + new Tag(VALID_TAG_FRIEND) + " "
                + new Tag(VALID_TAG_HUSBAND) + " "
                + new Tag(VALID_TAG_WIFE);

        assertCommandSuccess(new ShowTagCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    void execute_filteredPersons_success() {
        final Person amy = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withTags(VALID_TAG_FRIEND).build();

        final Person bob = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();

        final Person clara = new PersonBuilder().withName(VALID_NAME_CLARA).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_WIFE).build();

        AddressBook addressBook = new AddressBookBuilder().withPerson(amy).withPerson(bob)
                .withPerson(clara).build();
        UserPrefs userPrefs = new UserPrefs();

        Model model = new ModelManager(addressBook, userPrefs);
        Model expectedModel = new ModelManager(addressBook, userPrefs);

        model.updateFilteredPersonList(person -> person.getName().equals(VALID_NAME_AMY));
        expectedModel.updateFilteredPersonList(person -> person.getName().equals(VALID_NAME_AMY));

        String expectedMessage = ShowTagCommand.MESSAGE_SUCCESS + "\n"
                + new Tag(VALID_TAG_FRIEND) + " "
                + new Tag(VALID_TAG_HUSBAND) + " "
                + new Tag(VALID_TAG_WIFE);

        assertCommandSuccess(new ShowTagCommand(), model, expectedMessage, expectedModel);
    }
}
