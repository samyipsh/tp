package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.UserBrowser.openUrl;

import java.util.List;

import seedu.address.commons.core.UserBrowser;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Github;
import seedu.address.model.person.LinkedIn;
import seedu.address.model.person.Person;

public class OpenFieldCommand extends Command {

    public static final String COMMAND_WORD = "open";
    public static final String COMMAND_EXAMPLE = "Example: " + COMMAND_WORD + " 2 linkedin";
    public static final String MESSAGE_PARAMS = "Parameters: INDEXES (must be non-zero unsigned integers) "
            + "FIELD\n"
            + COMMAND_EXAMPLE;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens contacts' field information.\n"
            + COMMAND_EXAMPLE;
    public static final String MESSAGE_NO_DISPLAYED_PERSONS = "No persons displayed to open fields from.";
    public static final String MESSAGE_OUT_OF_BOUNDS_INDEX_DISPLAYED = "%1$d is an out-of-bounds index.\n"
            + "Indexes up to %2$d are valid.";
    public static final String MESSAGE_UNSUPPORTED_FIELD = "%s is an unsupported field.\n"
            + "Supported fields: Github, LinkedIn";

    public static final String MESSAGE_OPEN_FIELD_SUCCESS = "%s field of selected persons opened in browser"
            + " (if field present and valid)";

    private final String field;
    private final List<Index> targetIndexes;

    /**
     * Creates an OpenFieldCommand to open the specified field of the {@code Person}
     */
    public OpenFieldCommand(List<Index> targetIndexes, String field) {
        requireNonNull(targetIndexes);
        requireNonNull(field);

        this.targetIndexes = targetIndexes;
        this.field = field.trim().toLowerCase();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        openFields(model);

        return new CommandResult(String.format(MESSAGE_OPEN_FIELD_SUCCESS, field));
    }

    /**
     * Open fields of specified persons as determined by OpenFieldCommand object
     * Runs if {@link UserBrowser#isDisplayAndBrowseCompatible()}
     *
     * @param model
     * @throws CommandException
     */
    public void openFields(Model model) throws CommandException {
        if (!UserBrowser.isDisplayAndBrowseCompatible()) {
            return;
        }

        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        int listSize = lastShownList.size();

        requireNonEmptyList(listSize);
        requireIndexesWithinListBounds(targetIndexes, listSize);

        for (Index index: targetIndexes) {
            Person person = lastShownList.get(index.getZeroBased());
            openField(person);
        }
    }

    /**
     * Opens the specified field of a Person.
     * Only accepts valid fields of OpenField class
     * Assumes {@link UserBrowser#isDisplayAndBrowseCompatible()}
     *
     * @param person Person object whose field is to be opened in the browser
     * @throws CommandException
     */
    private void openField(Person person) throws CommandException {

        switch (field) {

        case "github":
            Github github = person.getGithub();

            if (!github.isEmpty()) {
                openUrl(github.getUrl());
            }
            break;

        case "linkedin":
            LinkedIn linkedIn = person.getLinkedin();

            if (!linkedIn.isEmpty()) {
                openUrl(linkedIn.getUrl());
            }
            break;

        //throw exception if supported fields are not present
        default:
            throw new CommandException(String.format(MESSAGE_UNSUPPORTED_FIELD, field));

        }
    }

    private void requireIndexesWithinListBounds(List<Index> indexes, int listSize) throws CommandException {
        for (Index i : indexes) {
            requireIndexWithinListBounds(i, listSize);
        }
    }

    private void requireIndexWithinListBounds(Index index, int listSize) throws CommandException {
        if (index.getZeroBased() >= listSize) {
            throw new CommandException(String.format(MESSAGE_OUT_OF_BOUNDS_INDEX_DISPLAYED,
                    index.getOneBased(), listSize));
        }
    }

    private void requireNonEmptyList(int listSize) throws CommandException {
        if (listSize == 0) {
            throw new CommandException(MESSAGE_NO_DISPLAYED_PERSONS);
        }
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
}


