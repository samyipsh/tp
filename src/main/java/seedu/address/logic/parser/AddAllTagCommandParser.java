package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddAllTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

public class AddAllTagCommandParser implements Parser<AddAllTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAllTagCommand
     * and returns an AddAllTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAllTagCommand parse(String args) throws ParseException {
        requireNonNull(args);

        try {
            Tag tagToAdd = ParserUtil.parseTag(args);
            return new AddAllTagCommand(tagToAdd);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAllTagCommand.MESSAGE_USAGE), pe);
        }


    }
}
