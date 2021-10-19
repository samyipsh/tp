package seedu.address.logic.commands;

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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class DelTagCommand extends Command {

    public static final String COMMAND_WORD = "delTag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete the tag of the person identified "
            + "by the index number used in the displayed person list. \n"
            + "Example: " + COMMAND_WORD + " 1 " + "friend";

    public static final String MESSAGE_DELETE_TAG_PERSON_SUCCESS = "Deleted $s Tag from $s";
    public static final String MESSAGE_TAG_NOT_EXIST = "This person do not have the tag: $s";

    private final Index index;
    private final Tag tagToDelete;

    public DelTagCommand(Index index, Tag tagToDelete) {
        requireNonNull(index);
        requireNonNull(tagToDelete);

        this.index = index;
        this.tagToDelete = tagToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDeleteTag = lastShownList.get(index.getZeroBased());
        Set<Tag> existingTags = personToDeleteTag.getTags();
        if (!existingTags.contains(tagToDelete)) {
            throw new CommandException(String.format(MESSAGE_TAG_NOT_EXIST, tagToDelete));
        }
        Person deleteTagPerson = delTag(personToDeleteTag);

        model.setPerson(personToDeleteTag, deleteTagPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_TAG_PERSON_SUCCESS, deleteTagPerson));
    }

    private Person delTag(Person personToDeleteTag) {
        Name name = personToDeleteTag.getName();
        Phone phone = personToDeleteTag.getPhone();
        Email email = personToDeleteTag.getEmail();
        Github github = personToDeleteTag.getGithub();
        LinkedIn linkedIn = personToDeleteTag.getLinkedin();
        Detail detail = personToDeleteTag.getDetail();
        Set<Tag> existingTags = personToDeleteTag.getTags();
        Set<Tag> updatedTags = new HashSet<>();
        updatedTags.addAll(existingTags);
        updatedTags.remove(tagToDelete);
        return new Person(name, phone, email, github, linkedIn, detail, updatedTags);

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DelTagCommand)) {
            return false;
        }

        return index.equals(((DelTagCommand) other).index)
                && tagToDelete.equals(((DelTagCommand) other).tagToDelete);
    }
}
