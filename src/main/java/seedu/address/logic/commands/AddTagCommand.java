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
 * Adds a tag to a person in the address book.
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "addtag";
    public static final String MESSAGE_PARAMS = "Parameters: INDEXES (must be positive integers) "
            + "[Tag]\n"
            + "Example: " + COMMAND_WORD + " 1 2 programmer";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to the persons identified "
            + "by the index numbers used in the displayed person list. "
            + "Will not add a duplicate existing tag.\n" + MESSAGE_PARAMS;
    public static final String MESSAGE_INVALID_INDEX_DISPLAYED = "%1$d is an invalid index.\n"
            + "Indexes up to %2$d are valid.";
    public static final String MESSAGE_TAG_PERSON_SUCCESS = "Persons tagged.";
    private final List<Index> targetIndexes;
    private final Tag tagToAdd;

    /**
     * Creates an AddTagCommand to add the specified {@code Tag} to the person
     * at {@code Index}.
     */
    public AddTagCommand(List<Index> targetIndexes, Tag tagToAdd) {
        this.targetIndexes = targetIndexes;
        this.tagToAdd = tagToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        tagPersons(model);


        return new CommandResult(MESSAGE_TAG_PERSON_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        // state check
        AddTagCommand atc = (AddTagCommand) other;
        return targetIndexes.equals(atc.targetIndexes)
                && tagToAdd.equals(atc.tagToAdd);
    }

    private Person addTag(Person personToTag) {
        Name name = personToTag.getName();
        Phone phone = personToTag.getPhone();
        Email email = personToTag.getEmail();
        Github github = personToTag.getGithub();
        LinkedIn linkedIn = personToTag.getLinkedin();
        Detail detail = personToTag.getDetail();
        Set<Tag> existingTags = personToTag.getTags();
        Set<Tag> updatedTags = new HashSet<>(existingTags);
        updatedTags.add(tagToAdd);
        return new Person(name, phone, email, github, linkedIn, detail, updatedTags);
    }

    private void tagPersons(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        for (Index index: targetIndexes) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(String.format(MESSAGE_INVALID_INDEX_DISPLAYED,
                        index.getOneBased(), lastShownList.size()));
            }

            Person personToTag = lastShownList.get(index.getZeroBased());
            Person taggedPerson = addTag(personToTag);

            model.setPerson(personToTag, taggedPerson);
        }
    }
}
