package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class OpenFieldCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Tag tag = new Tag("tag");
    private final Predicate<Person> predicateNoPersons = unused -> false;

    @Test
    public void execute_unfilteredList_success() {

    }
}
