package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DelTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new DelTagCommand object
 */
public class DelTagCommandParser implements Parser<DelTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DelTagCommmand
     * and returns an DelTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DelTagCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();

        Index index;

        Tag tag;

        try {
            String[] arguments = trimmedArgs.split("\\s", 2);

            if (arguments.length < 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DelTagCommand.MESSAGE_USAGE));
            }

            index = ParserUtil.parseIndex(arguments[0]);
            tag = ParserUtil.parseTag(arguments[1]);

        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage());
        }

        return new DelTagCommand(index, tag);
    }
}
