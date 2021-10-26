package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.OpenFieldCommand.MESSAGE_UNSUPPORTED_FIELD;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.OpenFieldCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class OpenFieldCommandParser implements Parser<OpenFieldCommand> {

    public static final String MESSAGE_INVALID_INDEX_AT = "Index at position %1$d is "
            + "not a non-zero unsigned integer.\n%2$s";


    /**
     * Parses the given {@code String} of arguments in the context of the OpenFieldCommand
     * and returns a OpenFieldCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public OpenFieldCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();

        String[] arguments = trimmedArgs.split("\\s+");
        requireAtLeastTwoArgs(arguments);

        String field = arguments[arguments.length - 1].toLowerCase();
        requireValidField(field);

        List<Index> indexes = parseIndexes(arguments);
        return new OpenFieldCommand(indexes, field);
    }

    private List<Index> parseIndexes(String[] args) throws ParseException {
        List<Index> indexes = new ArrayList<>();

        for (int i = 0; i < args.length - 1; i++) {
            try {
                indexes.add(ParserUtil.parseIndex(args[i]));
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_INDEX_AT, (i + 1),
                        OpenFieldCommand.MESSAGE_PARAMS));
            }
        }

        return indexes;
    }

    private void requireValidField(String field) throws ParseException {
        if (!(field.equals("github") || field.equals("linkedin"))) {
            throw new ParseException(String.format(MESSAGE_UNSUPPORTED_FIELD, field));
        }
    }

    private void requireAtLeastTwoArgs(String[] args) throws ParseException {
        if (args.length < 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenFieldCommand.MESSAGE_USAGE));
        }
    }




}

