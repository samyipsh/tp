package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

public class AliasCommandParser implements Parser<AliasCommand> {

    public static final String MESSAGE_INVALID_COMMAND_ALIAS = "%1$s is not an existing command word";

    public static final String MESSAGE_INVALID_ALIAS = "%1$s is an existing command word. "
            + "It cannot be used as an alias";

    @Override
    public AliasCommand parse(String userInput) throws ParseException {
        String[] arguments = userInput.trim().split("\\s+", 2);

        if (arguments.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        }

        String alias = parseAlias(arguments);
        String command = parseCommand(arguments);

        if (!Command.isExistingCommand(command)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_ALIAS, command));
        }

        if (Command.isExistingCommand(alias)) {
            throw new ParseException(String.format(MESSAGE_INVALID_ALIAS, alias));
        }

        return new AliasCommand(alias, command);
    }

    private String parseAlias(String[] args) {
        assert args.length == 2;
        return args[1];
    }

    private String parseCommand(String[] args) {
        assert args.length == 2;
        return args[0];
    }
}
