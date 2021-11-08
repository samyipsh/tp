package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;

/**
 * Wraps all data at the contact-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ContactBook implements ReadOnlyContactBook {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that use of non-static init blocks are not recommended. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public ContactBook() {}

    /**
     * Creates an ContactBook using the Persons in the {@code toBeCopied}
     */
    public ContactBook(ReadOnlyContactBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code ContactBook} with {@code newData}.
     */
    public void resetData(ReadOnlyContactBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the contact book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists
     * in the contact book excluding the one found at {@code personToExclude}.
     */
    public boolean hasPersonExcludingOtherPerson(Person person, Person personToExclude) {
        requireNonNull(person);
        requireNonNull(personToExclude);

        return persons.containsExcludingPerson(person, personToExclude);
    }

    /**
     * Adds a person to the contact book.
     * The person must not already exist in the contact book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the contact book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the contact book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code ContactBook}.
     * {@code key} must exist in the contact book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Returns the unmodifiable unique tags that exist in the contact book.
     */
    public ObservableSet<Tag> getUniqueTagList() {
        Set<Tag> tags = persons.asUnmodifiableObservableList()
                .stream()
                .flatMap(p -> p.getTags().stream())
                .collect(Collectors.toSet());

        ObservableSet<Tag> observableTags = FXCollections.observableSet(tags);
        return FXCollections.unmodifiableObservableSet(observableTags);
    }

    //// util methods

    /**
     * Returns the size of the contact book.
     */
    public int size() {
        return persons.length();
    }

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactBook // instanceof handles nulls
                && persons.equals(((ContactBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
