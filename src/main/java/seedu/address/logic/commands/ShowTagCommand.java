package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableSet;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Shows all tags that is in the address book.
 */
public class ShowTagCommand extends Command {

    public static final String COMMAND_WORD = "showtags";

    public static final String MESSAGE_SUCCESS = "Listed all tags";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableSet<Tag> tags = model.getUniqueTagList();

        String tagsAsString = tags.stream()
                .reduce("", (acc, tag) -> tag.toString() + " " + acc, (acc, tagString) -> tagString + " " + acc);

        return new CommandResult(MESSAGE_SUCCESS + "\n" + tagsAsString);
    }
}
