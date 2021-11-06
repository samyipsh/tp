package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAliasCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.OpenFieldCommand;
import seedu.address.logic.commands.ReplaceTagCommand;
import seedu.address.logic.commands.ShowAliasCommand;
import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.commands.ShowTagsCommand;
import seedu.address.logic.commands.TagAllCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.UntagAllCommand;
import seedu.address.logic.commands.UntagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Parses user input.
 */
public class ContactBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private Model model;

    public ContactBookParser(Model model) {
        this.model = model;
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        String unaliasedUserInput = replaceAlias(userInput);
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(unaliasedUserInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case ShowAliasCommand.COMMAND_WORD:
            return new ShowAliasCommand();

        case ShowCommand.COMMAND_WORD:
            return new ShowCommandParser().parse(arguments);

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case OpenFieldCommand.COMMAND_WORD:
            return new OpenFieldCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case TagCommand.COMMAND_WORD:
            return new TagCommandParser().parse(arguments);

        case TagAllCommand.COMMAND_WORD:
            return new TagAllCommandParser().parse(arguments);

        case UntagCommand.COMMAND_WORD:
            return new UntagCommandParser().parse(arguments);

        case UntagAllCommand.COMMAND_WORD:
            return new UntagAllCommandParser().parse(arguments);

        case ReplaceTagCommand.COMMAND_WORD:
            return new ReplaceTagCommandParser().parse(arguments);

        case ShowTagsCommand.COMMAND_WORD:
            return new ShowTagsCommand();

        case AliasCommand.COMMAND_WORD:
            return new AliasCommandParser().parse(arguments);

        case DeleteAliasCommand.COMMAND_WORD:
            return new DeleteAliasCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Replaces alias with the aliased command.
     */
    public String replaceAlias(String userInput) {
        Set<String> aliases = model.getExistingAlias();

        String longestMatchingAlias = null;

        // userInput with trailing space, as we want to replace the word, not a substring of a word
        // This quick fix works for one word input as well
        String userInputTrailingSpace = userInput + " ";

        for (String alias: aliases) {
            if (userInputTrailingSpace.indexOf(alias + " ") != 0) {
                continue;
            }

            if (longestMatchingAlias == null || longestMatchingAlias.length() < alias.length()) {
                longestMatchingAlias = alias;
            }
        }

        return longestMatchingAlias == null
                ? userInput
                : userInput.replaceFirst(longestMatchingAlias, model.getCorrespondingCommand(longestMatchingAlias));
    }
}
