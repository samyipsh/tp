package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class DeleteAliasCommand extends Command {
    public static final String COMMAND_WORD = "deletealias";

    public static final String MESSAGE_PARAMS = "Parameters: YOUR_ALIAS";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an existing alias for a command."
            + "\n"
            + MESSAGE_PARAMS;

    public static final String MESSAGE_SUCCESS = "Deleted alias '%1$s' for `%2$s` command";
    public static final String MESSAGE_NON_EXISTING = "The alias '%1$s' you specify does not exist";

    private String alias;

    /**
     * Creates an DeleteAliasCommand to delete an existing alias.
     */
    public DeleteAliasCommand(String alias) {
        requireNonNull(alias);
        this.alias = alias;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.containsAlias(alias)) {
            throw new CommandException(String.format(MESSAGE_NON_EXISTING, alias));
        }

        String command = model.getCorrespondingCommand(alias);

        model.deleteAlias(alias);

        return new CommandResult(String.format(MESSAGE_SUCCESS, alias, command));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteAliasCommand)) {
            return false;
        }

        // state check
        DeleteAliasCommand e = (DeleteAliasCommand) other;
        return alias.equals(e.alias);
    }
}
