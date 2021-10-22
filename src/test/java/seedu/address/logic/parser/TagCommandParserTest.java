package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TagCommand;
import seedu.address.model.tag.Tag;

public class TagCommandParserTest {
    private TagCommandParser parser = new TagCommandParser();
    private List<Index> firstIndex = new ArrayList<>();

    @Test
    public void parse_validArgs_success() {
        firstIndex.add(INDEX_FIRST_PERSON);

        assertParseSuccess(parser, "1 " + VALID_TAG_HUSBAND,
                new TagCommand(firstIndex, new Tag(VALID_TAG_HUSBAND)));
    }

    @Test
    public void parse_invalidIndex_failure() {
        // negative index
        assertParseFailure(parser, "-1 " + VALID_TAG_HUSBAND,
                String.format(TagCommandParser.MESSAGE_INVALID_INDEX_AT, 1, TagCommand.MESSAGE_PARAMS));

        // zero index
        assertParseFailure(parser, "0 " + VALID_TAG_HUSBAND,
                String.format(TagCommandParser.MESSAGE_INVALID_INDEX_AT, 1, TagCommand.MESSAGE_PARAMS));

        // signed-integer index
        assertParseFailure(parser, "+1 " + VALID_TAG_HUSBAND,
                String.format(TagCommandParser.MESSAGE_INVALID_INDEX_AT, 1, TagCommand.MESSAGE_PARAMS));

        // non-numeric index
        assertParseFailure(parser, "abc " + VALID_TAG_HUSBAND,
                String.format(TagCommandParser.MESSAGE_INVALID_INDEX_AT, 1, TagCommand.MESSAGE_PARAMS));
    }

    @Test
    public void parse_invalidTag_failure() {
        // non-alphanumeric
        assertParseFailure(parser, "1 " + "#hashtag",
                Tag.MESSAGE_CONSTRAINTS);

        // not single word, first word treated as an Index
        assertParseFailure(parser, "1 " + "good guy",
                String.format(TagCommandParser.MESSAGE_INVALID_INDEX_AT, 2, TagCommand.MESSAGE_PARAMS));
    }

    @Test
    public void parse_invalidFormat_failure() {

        // Not enough arguments
        assertParseFailure(parser, "only",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));

        // No arguments
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
    }

}
