package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.DelAllTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TagsPresentPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new DelAllTagCommand object
 */
public class DelAllTagCommandParser implements Parser<DelAllTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DelAllTagCommand
     * and returns an DelTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DelAllTagCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();

        Tag tag;
        TagsPresentPredicate predicate;

        try {
            String[] arguments = trimmedArgs.split("\\s+");

            if (arguments.length < 1 || trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DelAllTagCommand.MESSAGE_USAGE));
            }

            tag = ParserUtil.parseTag(arguments[0]);
            predicate = new TagsPresentPredicate(Arrays.asList(arguments[0]));

        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage());
        }

        return new DelAllTagCommand(tag, predicate);
    }

}
