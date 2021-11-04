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
 * Replaces specified tag with a new tag for all contacts in NetworkUS.
 */
public class ReplaceTagCommand extends Command {

    public static final String COMMAND_WORD = "replacetag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Replace the current tag to specified tag from all displayed contacts "
            + "Parameter: TAG(to be replaced) TAG(new Tag)\n"
            + "Example: " + COMMAND_WORD + " friend" + " enemy";

    public static final String MESSAGE_REPLACE_ALL_TAG_SUCCESS = "This tag has been replaced "
            + "from %s to %s for all displayed contacts";
    public static final String MESSAGE_TAG_NOT_EXIST = "This tag does not exist: %s";

    private final Tag tagToDelete;
    private final Tag tagToAdd;
    private final TagsPresentPredicate predicate;

    /**
     * Creates a ReplaceTagCommand to replace specified tag.
     *
     * @param tagToDelete Tag to be replaced for all displayed contacts.
     * @param tagToAdd Tag to be added as replacement.
     * @param predicate Predicate to check whether person contain the specified tag.
     */
    public ReplaceTagCommand(Tag tagToDelete, Tag tagToAdd, TagsPresentPredicate predicate) {
        requireNonNull(tagToDelete);
        requireNonNull(tagToAdd);

        this.tagToDelete = tagToDelete;
        this.tagToAdd = tagToAdd;
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
            Person personToReplaceTag = filteredList.get(0);
            Set<Tag> existingTags = personToReplaceTag.getTags();
            assert (existingTags.contains(tagToDelete));

            Person replaceTagPerson = replaceTag(personToReplaceTag);

            model.setPerson(personToReplaceTag, replaceTagPerson);
        }

        return new CommandResult(String.format(MESSAGE_REPLACE_ALL_TAG_SUCCESS, tagToDelete, tagToAdd));
    }

    private Person replaceTag(Person personToReplaceTag) {
        Name name = personToReplaceTag.getName();
        Phone phone = personToReplaceTag.getPhone();
        Email email = personToReplaceTag.getEmail();
        Github github = personToReplaceTag.getGithub();
        LinkedIn linkedIn = personToReplaceTag.getLinkedin();
        Detail detail = personToReplaceTag.getDetail();
        Set<Tag> existingTags = personToReplaceTag.getTags();
        Set<Tag> updatedTags = new HashSet<>();
        updatedTags.addAll(existingTags);
        updatedTags.remove(tagToDelete);
        updatedTags.add(tagToAdd);
        return new Person(name, phone, email, github, linkedIn, detail, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReplaceTagCommand)) {
            return false;
        }

        return tagToDelete.equals(((ReplaceTagCommand) other).tagToDelete)
                && tagToAdd.equals(((ReplaceTagCommand) other).tagToAdd)
                && predicate.equals(((ReplaceTagCommand) other).predicate);
    }

}
