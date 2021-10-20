package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DelTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new DelTagCommand object
 */
public class DelTagCommandParser implements Parser<DelTagCommand> {

    public static final String MESSAGE_INVALID_INDEX_AT = "Index at position %1$d is "
            + "not a non-zero unsigned integer.\n%2$s";

    /**
     * Parses the given {@code String} of arguments in the context of the DelTagCommand
     * and returns an DelTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DelTagCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();

        List<Index> indexes;

        Tag tag;

        try {
            String[] arguments = trimmedArgs.split("\\s");

            if (arguments.length < 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DelTagCommand.MESSAGE_USAGE));
            }

            indexes = parseIndexes(arguments);
            tag = parseTagAtEnd(arguments);

        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage());
        }

        assert(indexes.size() != 0);

        return new DelTagCommand(indexes, tag);
    }

    private List<Index> parseIndexes(String[] args) throws ParseException {
        List<Index> indexes = new ArrayList<>();

        for (int i = 0; i < args.length - 1; i++) {
            try {
                indexes.add(ParserUtil.parseIndex(args[i]));
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_INDEX_AT, (i + 1),
                        DelTagCommand.MESSAGE_PARAMS));
            }
        }

        return indexes;
    }

    private Tag parseTagAtEnd(String[] args) throws ParseException {
        return ParserUtil.parseTag(args[args.length - 1]);
    }
}
