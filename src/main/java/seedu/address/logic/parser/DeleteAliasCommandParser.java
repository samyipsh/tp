package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteAliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteAliasCommandParser implements Parser<DeleteAliasCommand> {
    public static final String MESSAGE_INVALID_COMMAND_ALIAS = "%1$s is not an existing command word";

    public static final String MESSAGE_INVALID_ALIAS = "%1$s is an existing command word. "
            + "It cannot be used as an alias";

    @Override
    public DeleteAliasCommand parse(String userInput) throws ParseException {
        String[] arguments = userInput.trim().split("\\s+", 1);

        if (arguments.length != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAliasCommand.MESSAGE_USAGE));
        }

        String alias = parseAlias(arguments);
        return new DeleteAliasCommand(alias);
    }

    private String parseAlias(String[] args) {
        assert args.length == 1;
        return args[0];
    }
}
