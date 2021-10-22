package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ReplaceTagCommand;
import seedu.address.model.person.TagsPresentPredicate;
import seedu.address.model.tag.Tag;

class ReplaceTagCommandParserTest {
    private ReplaceTagCommandParser parser = new ReplaceTagCommandParser();

    @Test
    void parse_validArgs_success() {
        Tag validDeleteTag = new Tag(VALID_TAG_HUSBAND);
        Tag validAddTag = new Tag(VALID_TAG_FRIEND);
        TagsPresentPredicate validPredicate = new TagsPresentPredicate(Arrays.asList(VALID_TAG_HUSBAND));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, VALID_TAG_HUSBAND + " " + VALID_TAG_FRIEND,
                new ReplaceTagCommand(validDeleteTag, validAddTag, validPredicate));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, VALID_TAG_HUSBAND + "    \n" + VALID_TAG_FRIEND + " \n",
                new ReplaceTagCommand(validDeleteTag, validAddTag, validPredicate));

        // multiple tags
        assertParseSuccess(parser, VALID_TAG_HUSBAND + " " + VALID_TAG_FRIEND + " clown",
                new ReplaceTagCommand(validDeleteTag, validAddTag, validPredicate));

    }

    @Test
    public void parse_invalidFormat_failure() {

        // Not enough arguments
        assertParseFailure(parser, "CS2100",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReplaceTagCommand.MESSAGE_USAGE));

        // No arguments
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReplaceTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidTag_throwsParseException() {
        // non-alphanumeric deleteTag
        assertParseFailure(parser, "C$2100 CS2040", Tag.MESSAGE_CONSTRAINTS);

        // non-alphanumeric addTag
        assertParseFailure(parser, "CS2100 C$2040", Tag.MESSAGE_CONSTRAINTS);
    }

}
