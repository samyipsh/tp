package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteAliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteAliasCommandParser implements Parser<DeleteAliasCommand> {

    @Override
    public DeleteAliasCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        String arg = userInput.trim();

        if (arg.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAliasCommand.MESSAGE_USAGE));
        }
        return new DeleteAliasCommand(arg);
    }

}
