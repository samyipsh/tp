package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.TagAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

public class TagAllCommandParser implements Parser<TagAllCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagAllCommand
     * and returns a TagAllCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagAllCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (args.equals("")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagAllCommand.MESSAGE_USAGE));
        }

        try {
            Tag tagToAdd = ParserUtil.parseTag(args);
            return new TagAllCommand(tagToAdd);
        } catch (ParseException pe) {
            throw new ParseException(
                    pe.getMessage(), pe);
        }


    }
}
