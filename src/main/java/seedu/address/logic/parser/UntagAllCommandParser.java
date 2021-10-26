package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.UntagAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TagsPresentPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new UntagAllCommand object
 */
public class UntagAllCommandParser implements Parser<UntagAllCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UntagAllCommand
     * and returns an UntagAllCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UntagAllCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();

        Tag tag;
        TagsPresentPredicate predicate;

        try {
            String[] arguments = trimmedArgs.split("\\s+");

            if (arguments.length < 1 || trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, UntagAllCommand.MESSAGE_USAGE));
            }

            tag = ParserUtil.parseTag(arguments[0]);
            predicate = new TagsPresentPredicate(Arrays.asList(arguments[0]));

        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage());
        }

        return new UntagAllCommand(tag, predicate);
    }

}
