package seedu.address.logic.commands;

import seedu.address.model.Model;

public class ShowAliasCommand extends Command {
    public static final String COMMAND_WORD = "showalias";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows Registered Aliases.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened Alias Mapping Window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false, true);
    }
}
