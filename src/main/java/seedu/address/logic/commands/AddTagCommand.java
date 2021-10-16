package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Detail;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.LinkedIn;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Adds a tag to a person in the address book.
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "addtag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to the person identified "
            + "by the index number used in the displayed person list. "
            + "Cannot add an already existing tag\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[Tag]\n"
            + "Example: " + COMMAND_WORD + " 1 programmer";
    public static final String MESSAGE_TAG_PERSON_SUCCESS = "Tagged Person: %1$s";
    private final Index targetIndex;
    private final Tag tagToAdd;

    /**
     * Creates an AddTagCommand to add the specified {@code Tag} to the person
     * at {@code Index}.
     */
    public AddTagCommand(Index targetIndex, Tag tagToAdd) {
        this.targetIndex = targetIndex;
        this.tagToAdd = tagToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToTag = lastShownList.get(targetIndex.getZeroBased());
        Person taggedPerson = addTag(personToTag);

        model.setPerson(personToTag, taggedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_TAG_PERSON_SUCCESS, taggedPerson));
    }

    private Person addTag(Person personToTag) {
        Name name = personToTag.getName();
        Phone phone = personToTag.getPhone();
        Email email = personToTag.getEmail();
        Github github = personToTag.getGithub();
        LinkedIn linkedIn = personToTag.getLinkedin();
        Detail detail = personToTag.getDetail();
        Set<Tag> existingTags = personToTag.getTags();
        Set<Tag> updatedTags = new HashSet<>();
        updatedTags.addAll(existingTags);
        updatedTags.add(tagToAdd);
        return new Person(name, phone, email, github, linkedIn, detail, updatedTags);
    }
}
