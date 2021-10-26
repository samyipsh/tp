package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.TagAllCommand;

class AliasCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE);

    private static final String ALIAS_NO_SPACE = "myalias";
    private static final String ALIAS_WITH_SPACES = "oh my alias";
    private static final String INVALID_COMMAND = "nonexist";

    private AliasCommandParser parser = new AliasCommandParser();

    @Test
    void parse_missingParts_failure() {
        // Empty arguments
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);

        // Missing alias
        assertParseFailure(parser, EditCommand.COMMAND_WORD, MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_invalidCommandWord_failure() {
        assertParseFailure(parser, INVALID_COMMAND + " " + ALIAS_WITH_SPACES,
                String.format(AliasCommandParser.MESSAGE_INVALID_COMMAND_ALIAS, INVALID_COMMAND));
    }

    @Test
    void parse_aliasNoSpace_success() {
        assertParseSuccess(parser, EditCommand.COMMAND_WORD + " " + ALIAS_NO_SPACE,
                new AliasCommand(ALIAS_NO_SPACE, EditCommand.COMMAND_WORD));
    }

    @Test
    void parse_aliasWithSpace_success() {
        assertParseSuccess(parser, TagAllCommand.COMMAND_WORD + " " + ALIAS_WITH_SPACES,
                new AliasCommand(ALIAS_WITH_SPACES, TagAllCommand.COMMAND_WORD));
    }

    @Test
    void parse_allValidCommands_success() {
        List<String> existingCommandWords = Command.getExistingCommandWords();

        for (String commandWord : existingCommandWords) {
            String userInput = commandWord + " " + ALIAS_WITH_SPACES;
            AliasCommand expectedCommand = new AliasCommand(ALIAS_WITH_SPACES, commandWord);

            assertParseSuccess(parser, userInput, expectedCommand);
        }
    }
}
