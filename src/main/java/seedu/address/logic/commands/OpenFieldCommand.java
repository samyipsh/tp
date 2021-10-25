package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.UserBrowser.openUrl;

import java.util.List;

import seedu.address.commons.core.UserBrowser;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class OpenFieldCommand extends Command {

    public static final String COMMAND_WORD = "open";
    public static final String MESSAGE_PARAMS = "Parameters: INDEXES (must be non-zero unsigned integers) "
            + "FIELD\n"
            + "Example: " + COMMAND_WORD + " 1 2 programmer";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens contacts's field information.\n"
            + "Example: " + COMMAND_WORD + " 2 linkedin";
    public static final String MESSAGE_NO_DISPLAYED_PERSONS = "No persons displayed to open fields from.";
    public static final String MESSAGE_OUT_OF_BOUNDS_INDEX_DISPLAYED = "%1$d is an out-of-bounds index.\n"
            + "Indexes up to %2$d are valid.";
    public static final String MESSAGE_UNSUPPORTED_FIELD = "%s is an unsupported field.\n"
            + "Supported fields: Github, LinkedIn";

    public static final String MESSAGE_OPEN_FIELD_SUCCESS = "%s field opened of selected persons opened in browser";

    private final String field;
    private final List<Index> targetIndexes;

    /**
     * Creates an OpenFieldCommand to open the specified field of the {@code Person}
     */
    public OpenFieldCommand(List<Index> targetIndexes, String field) {
        requireNonNull(targetIndexes);
        requireNonNull(field);

        this.targetIndexes = targetIndexes;
        this.field = field;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        openFields(model);

        return new CommandResult(String.format(MESSAGE_OPEN_FIELD_SUCCESS, field));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCommand)) {
            return false;
        }

        // state check
        OpenFieldCommand otherCommand = (OpenFieldCommand) other;
        return otherCommand.targetIndexes.equals(targetIndexes)
                && otherCommand.field.equals(field);
    }

    public void openFields(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (lastShownList.size() == 0) {
            throw new CommandException(MESSAGE_NO_DISPLAYED_PERSONS);
        }

        for (Index index: targetIndexes) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(String.format(MESSAGE_OUT_OF_BOUNDS_INDEX_DISPLAYED,
                        index.getOneBased(), lastShownList.size()));
            }

            Person person = lastShownList.get(index.getZeroBased());
            openField(person);
        }
    }

    public void openField(Person person) throws CommandException {
        String url;

        switch (field) {

        case "github":
            url = person.getGithub().getUrl();
            break;

        case "linkedin":
            url = person.getLinkedin().getUrl();
            break;

        default:
            throw new CommandException(String.format(MESSAGE_UNSUPPORTED_FIELD, field));
        }

        openUrl(url);
    }
}


