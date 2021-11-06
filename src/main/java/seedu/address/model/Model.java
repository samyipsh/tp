package seedu.address.model;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.alias.AliasTable;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' alias table.
     */
    AliasTable getAliasTable();

    /**
     * Sets the user prefs' alias table.
     */
    void setAliasTable(AliasTable aliasTable);

    /**
     * Adds the given alias for a command.
     * {@code command} must be an existing command.
     */
    void addAlias(String alias, String command);

    /**
     * Returns a set of existing aliases.
     */
    Set<String> getExistingAlias();

    /**
     * Returns what command does {@code alias} represent.
     */
    String getCorrespondingCommand(String alias);

    boolean containsAlias(String alias);

    void deleteAlias(String alias);

    /**
     * Returns the user prefs' contact book file path.
     */
    Path getContactBookFilePath();

    /**
     * Sets the user prefs' contact book file path.
     */
    void setContactBookFilePath(Path contactBookFilePath);

    /**
     * Replaces contact book data with the data in {@code contactBook}.
     */
    void setContactBook(ReadOnlyContactBook contactBook);

    /** Returns the ContactBook */
    ReadOnlyContactBook getContactBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the contact book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a person with the same identity as {@code person}
     * exists in the contact book excluding the one at {@code personToExclude} once.
     */
    boolean hasPersonExcludingOtherPerson(Person person, Person personToExclude);

    /**
     * Deletes the given person.
     * The person must exist in the contact book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the contact book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the contact book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the contact book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /** Gets the unmodifiable unique tag list of all persons in the contact book */
    ObservableSet<Tag> getUniqueTagList();
}
