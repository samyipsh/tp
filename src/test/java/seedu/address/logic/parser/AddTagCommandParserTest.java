package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTagCommand;
import seedu.address.model.tag.Tag;

public class AddTagCommandParserTest {
    private AddTagCommandParser parser = new AddTagCommandParser();

    @Test
    public void parse_validArgs_success() {
        assertParseSuccess(parser, "1 " + VALID_TAG_HUSBAND,
                new AddTagCommand(INDEX_FIRST_PERSON, new Tag(VALID_TAG_HUSBAND)));
    }

    @Test
    public void parse_invalidIndex_failure() {
        // negative index
        assertParseFailure(parser, "-1 " + VALID_TAG_HUSBAND,
                ParserUtil.MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0 " + VALID_TAG_HUSBAND,
                ParserUtil.MESSAGE_INVALID_INDEX);

        // non-numeric index
        assertParseFailure(parser, "abc " + VALID_TAG_HUSBAND,
                ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidTag_failure() {
        // non-alphanumeric
        assertParseFailure(parser, "1 " + "#hashtag",
                Tag.MESSAGE_CONSTRAINTS);

        // not single word
        assertParseFailure(parser, "1 " + "good guy",
                Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidFormat_failure() {

        // Not enough arguments
        assertParseFailure(parser, "only",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));

        // No arguments
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }

}
