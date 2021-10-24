package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Detail;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.LinkedIn;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TagsPresentPredicate;
import seedu.address.model.tag.Tag;

/**
 * Deletes specified tag from all contacts in NetworkUS.
 */
public class DelAllTagCommand extends Command {

    public static final String COMMAND_WORD = "delalltag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete the specified tag from all displayed contacts "
            + "Parameter: TAG\n"
            + "Example: " + COMMAND_WORD + "friend";

    public static final String MESSAGE_DELETE_ALL_TAG_SUCCESS = "This tag has been deleted for all "
            + "displayed contacts: %s";
    public static final String MESSAGE_TAG_NOT_EXIST = "This tag does not exist: %s";

    private final Tag tagToDelete;
    private final TagsPresentPredicate predicate;

    /**
     * Creates a DelAllTagCommand to delete the specified {@code Tag} from all contacts.
     *
     * @param tagToDelete Tag to be deleted from all contacts.
     * @param predicate Predicate to check whether person contain the specified tag.
     */
    public DelAllTagCommand(Tag tagToDelete, TagsPresentPredicate predicate) {
        requireNonNull(tagToDelete);
        requireNonNull(predicate);

        this.tagToDelete = tagToDelete;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> filteredList = model.getFilteredPersonList().filtered(predicate);
        int numPerson = filteredList.size();

        if (filteredList.size() == 0) {
            throw new CommandException(String.format(MESSAGE_TAG_NOT_EXIST, tagToDelete));
        }

        for (int i = 0; i < numPerson; i++) {
            Person personToDeleteTag = filteredList.get(0);
            Set<Tag> existingTags = personToDeleteTag.getTags();
            assert (existingTags.contains(tagToDelete));

            Person deleteTagPerson = delTag(personToDeleteTag);

            model.setPerson(personToDeleteTag, deleteTagPerson);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_ALL_TAG_SUCCESS, tagToDelete));
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
        if (!(other instanceof DelAllTagCommand)) {
            return false;
        }

        return tagToDelete.equals(((DelAllTagCommand) other).tagToDelete)
                && predicate.equals(((DelAllTagCommand) other).predicate);
    }
}
