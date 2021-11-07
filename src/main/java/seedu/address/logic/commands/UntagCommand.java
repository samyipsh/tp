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
 * Untags a tag from a person in the NetworkUS.
 */
public class UntagCommand extends Command {

    public static final String COMMAND_WORD = "untag";

    public static final String MESSAGE_PARAMS = "Parameters: INDEXES (must be non-zero unsigned integers) "
            + "TAG\n"
            + "Example: " + COMMAND_WORD + " 1 2 programmer";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete the tag of the person identified "
            + "by the index numbers used in the displayed person list. \n"
            + "Parameter: INDEXES TAG \n"
            + "Example: " + COMMAND_WORD + " 1 " + "friend";

    public static final String MESSAGE_UNTAG_PERSON_SUCCESS = "Deleted %s Tag";
    public static final String MESSAGE_NO_DISPLAYED_PERSONS = "No persons displayed to untag.";
    public static final String MESSAGE_OUT_OF_BOUNDS_INDEX_DISPLAYED = "%1$d is an out-of-bounds index.\n"
            + "Indexes up to %2$d are valid.";
    public static final String MESSAGE_TAG_NOT_EXIST = "None of the selected persons contain the tag: %s";

    private final List<Index> targetIndexes;
    private final Tag tagToDelete;
    private boolean isTagPresent;

    /**
     * Creates an UntagCommand to delete the specified {@code Tag} from the person
     * at {@code Index}
     */
    public UntagCommand(List<Index> targetIndexes, Tag tagToDelete) {
        requireNonNull(targetIndexes);
        requireNonNull(tagToDelete);

        this.targetIndexes = targetIndexes;
        this.tagToDelete = tagToDelete;
        this.isTagPresent = false;
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
        if (updatedTags.remove(tagToDelete)) {
            isTagPresent = true;
        }

        return new Person(name, phone, email, github, linkedIn, detail, updatedTags);

    }

    private void untagPerson(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Set<Person> personsToUntag = new HashSet<>();

        if (lastShownList.size() == 0) {
            throw new CommandException(MESSAGE_NO_DISPLAYED_PERSONS);
        }

        for (Index index: targetIndexes) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(String.format(MESSAGE_OUT_OF_BOUNDS_INDEX_DISPLAYED,
                        index.getOneBased(), lastShownList.size()));
            }

            Person person = lastShownList.get(index.getZeroBased());
            personsToUntag.add(person);

        }

        for (Person personToUntag: personsToUntag) {
            Person untaggedPerson = untag(personToUntag);
            model.setPerson(personToUntag, untaggedPerson);
        }

        if (!isTagPresent) {
            throw new CommandException(String.format(MESSAGE_TAG_NOT_EXIST, tagToDelete));
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
