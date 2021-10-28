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
import seedu.address.model.tag.Tag;

public class TagAllCommand extends Command {

    public static final String COMMAND_WORD = "tagall";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": tags all displayed persons with the specified tag.\n"
            + "Parameters: TAG (must consist of letters and numbers without whitespaces)\n"
            + "Example: " + COMMAND_WORD + " programmer";

    public static final String MESSAGE_SUCCESS = "All displayed persons tagged.";
    public static final String MESSAGE_NO_PERSONS_TO_TAG = "No persons displayed to tag.";

    private final Tag tagToAdd;

    /**
     * Creates a TagAllCommand to add the specified {@code Tag} to all displayed persons
     */
    public TagAllCommand(Tag tagToAdd) {
        requireNonNull(tagToAdd);
        this.tagToAdd = tagToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        tagDisplayedPersons(model);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagAllCommand)) {
            return false;
        }

        // state check
        TagAllCommand t = (TagAllCommand) other;
        return tagToAdd.equals(t.tagToAdd);
    }

    private void tagDisplayedPersons(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> displayedList = model.getFilteredPersonList();

        if (displayedList.size() == 0) {
            throw new CommandException(MESSAGE_NO_PERSONS_TO_TAG);
        }

        for (Person personToTag : displayedList) {
            Person taggedPerson = addTag(personToTag);

            model.setPerson(personToTag, taggedPerson);
        }
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
}
