package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ReplaceTagCommand;
import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.commands.ShowTagsCommand;
import seedu.address.logic.commands.TagAllCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.UntagAllCommand;
import seedu.address.logic.commands.UntagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.FoundInNameOrTagsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.TagsPresentPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class ContactBookParserTest {

    private final ContactBookParser parser = new ContactBookParser(new ModelManager());

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new FoundInNameOrTagsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_addTag() throws Exception {
        List<Index> firstIndex = Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        String validTagName = "programmer";
        Tag tag = ParserUtil.parseTag(validTagName);
        TagCommand command = (TagCommand) parser.parseCommand(
                TagCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                        + " " + INDEX_SECOND_PERSON.getOneBased() + " " + validTagName);
        assertEquals(new TagCommand(firstIndex, tag), command);
    }

    @Test
    public void parseCommand_untag() throws Exception {
        List<Index> firstIndex = Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        String validTagName = "programmer";
        Tag tag = ParserUtil.parseTag(validTagName);
        UntagCommand command = (UntagCommand) parser.parseCommand(
                UntagCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                        + " " + INDEX_SECOND_PERSON.getOneBased() + " " + validTagName);
        assertEquals(new UntagCommand(firstIndex, tag), command);
    }

    @Test
    public void parseCommand_untagAll() throws Exception {
        String validTagName = "programmer";
        Tag tag = ParserUtil.parseTag(validTagName);
        TagsPresentPredicate predicate = new TagsPresentPredicate(Collections.singletonList(validTagName));
        UntagAllCommand command = (UntagAllCommand) parser.parseCommand(
                UntagAllCommand.COMMAND_WORD + " " + validTagName);
        assertEquals(new UntagAllCommand(tag, predicate), command);
    }

    @Test
    public void parseCommand_replaceTag() throws Exception {
        String validDeleteTagName = "noob";
        String validAddTagName = "programmer";
        Tag deleteTag = ParserUtil.parseTag(validDeleteTagName);
        Tag addTag = ParserUtil.parseTag(validAddTagName);
        TagsPresentPredicate predicate = new TagsPresentPredicate(Collections.singletonList(validDeleteTagName));
        ReplaceTagCommand command = (ReplaceTagCommand) parser.parseCommand(
                ReplaceTagCommand.COMMAND_WORD + " " + validDeleteTagName + " " + validAddTagName);
        assertEquals(new ReplaceTagCommand(deleteTag, addTag, predicate), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }
    @Test
    public void parseCommand_show() throws Exception {
        ShowCommand command = (ShowCommand) parser.parseCommand(
                ShowCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new ShowCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_addAllTag() throws Exception {
        TagAllCommand command = (TagAllCommand) parser.parseCommand(
                TagAllCommand.COMMAND_WORD + " " + VALID_TAG_HUSBAND);
        assertEquals(new TagAllCommand(ParserUtil.parseTag(VALID_TAG_HUSBAND)), command);
    }

    @Test
    public void parseCommand_showtags() throws Exception {
        assertTrue(parser.parseCommand(ShowTagsCommand.COMMAND_WORD) instanceof ShowTagsCommand);
        assertTrue(parser.parseCommand(ShowTagsCommand.COMMAND_WORD + " 3") instanceof ShowTagsCommand);
    }

    @Test
    public void parseCommand_alias() throws Exception {
        String alias = "tag -A";
        String command = TagAllCommand.COMMAND_WORD;

        assertEquals(parser.parseCommand(AliasCommand.COMMAND_WORD + " " + command + " " + alias),
                new AliasCommand(alias, command));
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void replaceAlias() {
        Model model = new ModelManager();
        model.addAlias("h", "help");
        model.addAlias("ta", "tagall");

        ContactBookParser parser = new ContactBookParser(model);

        // EP: no-param commands
        assertEquals("help", parser.replaceAlias("h"));

        // EP: param commands
        assertEquals("tagall os", parser.replaceAlias("ta os"));

        // EP: matching substring (not whole string) that matches at index 0
        assertEquals("taga", parser.replaceAlias("taga"));

        // EP: matching word whose matching index != 0
        assertEquals("tagall ta", parser.replaceAlias("tagall ta"));
    }
}
