package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Creates an alias for a command.
 */
public class AliasCommand extends Command {

    public static final String COMMAND_WORD = "alias";

    public static final String MESSAGE_PARAMS = "Parameters: COMMAND_WORD YOUR_ALIAS";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an alias for a command. "
            + "Command to be aliased must be a valid command.\n"
            + MESSAGE_PARAMS;

    public static final String MESSAGE_SUCCESS = "Created alias '%1$s' as `%2$s` command";

    private String alias;
    private String command;

    /**
     * Creates an AliasCommand to create alias for a certain existing command.
     */
    public AliasCommand(String alias, String command) {
        requireNonNull(alias);
        requireNonNull(command);

        assert Command.isExistingCommand(command);

        this.alias = alias;
        this.command = command;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.addAlias(alias, command);
        return new CommandResult(String.format(MESSAGE_SUCCESS, alias, command));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AliasCommand)) {
            return false;
        }

        // state check
        AliasCommand e = (AliasCommand) other;
        return alias.equals(e.alias)
                && command.equals(e.command);
    }
}
