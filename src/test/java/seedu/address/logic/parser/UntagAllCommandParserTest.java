package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UntagAllCommand;
import seedu.address.model.person.TagsPresentPredicate;
import seedu.address.model.tag.Tag;

class UntagAllCommandParserTest {
    private UntagAllCommandParser parser = new UntagAllCommandParser();

    @Test
    void parse_validArgs_success() {
        Tag validTag = new Tag(VALID_TAG_HUSBAND);
        TagsPresentPredicate validPredicate = new TagsPresentPredicate(Arrays.asList(VALID_TAG_HUSBAND));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, VALID_TAG_HUSBAND, new UntagAllCommand(validTag, validPredicate));

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "  \n" + VALID_TAG_HUSBAND, new UntagAllCommand(validTag, validPredicate));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UntagAllCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidTag_throwsParseException() {
        // non-alphanumeric
        assertParseFailure(parser, "C$2100", Tag.MESSAGE_CONSTRAINTS);
    }

}
