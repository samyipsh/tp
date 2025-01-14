package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ContactBook;
import seedu.address.model.Model;

/**
 * Clears the contact book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "NetworkUS has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setContactBook(new ContactBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
