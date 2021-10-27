package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class AliasCommandTest {

    @Test
    public void constructor_nullParam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AliasCommand(null, "command"));
        assertThrows(NullPointerException.class, () -> new AliasCommand("alias", null));
    }

    @Test
    public void equals() {
        String alias = "tag -D";
        String untagCommand = UntagCommand.COMMAND_WORD;
        String tagCommand = TagCommand.COMMAND_WORD;

        AliasCommand aliasUntag = new AliasCommand(alias, untagCommand);
        AliasCommand aliasTag = new AliasCommand(alias, tagCommand);

        // same object -> returns true
        assertTrue(aliasUntag.equals(aliasUntag));

        // same values -> returns true
        AliasCommand aliasUntagCopy = new AliasCommand(alias, untagCommand);
        assertTrue(aliasUntag.equals(aliasUntagCopy));

        // different types -> returns false
        assertFalse(aliasUntag.equals(1));

        // null -> returns false
        assertFalse(aliasUntag.equals(null));

        // different person -> returns false
        assertFalse(aliasUntag.equals(aliasTag));
    }
}
