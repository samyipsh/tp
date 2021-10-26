package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import java.util.List;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    private static final List<String> COMMAND_WORDS = List.of(
            AddCommand.COMMAND_WORD,
            AliasCommand.COMMAND_WORD,
            ClearCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD,
            EditCommand.COMMAND_WORD,
            ExitCommand.COMMAND_WORD,
            FindCommand.COMMAND_WORD,
            HelpCommand.COMMAND_WORD,
            ListCommand.COMMAND_WORD,
            ReplaceTagCommand.COMMAND_WORD,
            ShowTagsCommand.COMMAND_WORD,
            TagAllCommand.COMMAND_WORD,
            TagCommand.COMMAND_WORD,
            UntagAllCommand.COMMAND_WORD,
            UntagCommand.COMMAND_WORD
    );

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    public static boolean isExistingCommand(String commandWord) {
        return COMMAND_WORDS.stream().anyMatch(existingCommandWord -> existingCommandWord.equals(commandWord));
    }
}
