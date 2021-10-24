package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
 * Deletes a tag to a person in the address book.
 */
public class UntagCommand extends Command {

    public static final String COMMAND_WORD = "untag";

    public static final String MESSAGE_PARAMS = "Parameters: INDEXES (must be non-zero unsigned integers) "
            + "TAG\n"
            + "Example: " + COMMAND_WORD + " 1 2 programmer";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete the tag of the person identified "
            + "by the index numbers used in the displayed person list. \n"
            + "Parameter: INDEX TAG \n"
            + "Example: " + COMMAND_WORD + " 1 " + "friend";

    public static final String MESSAGE_UNTAG_PERSON_SUCCESS = "Deleted %s Tag";
    public static final String MESSAGE_NO_DISPLAYED_PERSONS = "No persons displayed to tag.";
    public static final String MESSAGE_OUT_OF_BOUNDS_INDEX_DISPLAYED = "%1$d is an out-of-bounds index.\n"
            + "Indexes up to %2$d are valid.";

    private final List<Index> targetIndexes;
    private final Tag tagToDelete;

    /**
     * Creates an UntagCommand to delete the specified {@code Tag} to the person
     * at {@code Index}
     */
    public UntagCommand(List<Index> targetIndexes, Tag tagToDelete) {
        requireNonNull(targetIndexes);
        requireNonNull(tagToDelete);

        this.targetIndexes = targetIndexes;
        this.tagToDelete = tagToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        untagPerson(model);

        return new CommandResult(String.format(MESSAGE_UNTAG_PERSON_SUCCESS, tagToDelete));

    }

    private Person untag(Person personToDeleteTag) {
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

    private void untagPerson(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (lastShownList.size() == 0) {
            throw new CommandException(MESSAGE_NO_DISPLAYED_PERSONS);
        }

        for (Index index: targetIndexes) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(String.format(MESSAGE_OUT_OF_BOUNDS_INDEX_DISPLAYED,
                        index.getOneBased(), lastShownList.size()));
            }

            Person personToTag = lastShownList.get(index.getZeroBased());
            Person taggedPerson = untag(personToTag);

            model.setPerson(personToTag, taggedPerson);
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UntagCommand)) {
            return false;
        }

        return targetIndexes.equals(((UntagCommand) other).targetIndexes)
                && tagToDelete.equals(((UntagCommand) other).tagToDelete);
    }
}
