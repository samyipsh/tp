package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.alias.AliasTable;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the contact book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ContactBook contactBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given contactBook and userPrefs.
     */
    public ModelManager(ReadOnlyContactBook contactBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(contactBook, userPrefs);

        logger.fine("Initializing with address book: " + contactBook + " and user prefs " + userPrefs);

        this.contactBook = new ContactBook(contactBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.contactBook.getPersonList());
    }

    public ModelManager() {
        this(new ContactBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public AliasTable getAliasTable() {
        return userPrefs.getAliasTable();
    }

    @Override
    public void setAliasTable(AliasTable aliasTable) {
        requireNonNull(aliasTable);
        userPrefs.setAliasTable(aliasTable);
    }

    @Override
    public void addAlias(String alias, String command) {
        requireNonNull(alias);
        requireNonNull(command);
        userPrefs.addAlias(alias, command);
    }

    @Override
    public Set<String> getExistingAlias() {
        return userPrefs.getExistingAliases();
    }

    @Override
    public String getCorrespondingCommand(String alias) {
        requireNonNull(alias);
        return userPrefs.getCorrespondingCommand(alias);
    }

    @Override
    public boolean containsAlias(String alias) {
        requireNonNull(alias);
        return userPrefs.containsAlias(alias);
    }

    @Override
    public void deleteAlias(String alias) {
        requireNonNull(alias);
        userPrefs.deleteAlias(alias);
    }

    @Override
    public Path getContactBookFilePath() {
        return userPrefs.getContactBookFilePath();
    }

    @Override
    public void setContactBookFilePath(Path contactBookFilePath) {
        requireNonNull(contactBookFilePath);
        userPrefs.setContactBookFilePath(contactBookFilePath);
    }

    //=========== ContactBook ================================================================================

    @Override
    public void setContactBook(ReadOnlyContactBook contactBook) {
        this.contactBook.resetData(contactBook);
    }

    @Override
    public ReadOnlyContactBook getContactBook() {
        return contactBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return contactBook.hasPerson(person);
    }

    @Override
    public boolean hasPersonExcludingOtherPerson(Person person, Person personToExclude) {
        requireNonNull(person);
        requireNonNull(personToExclude);

        return contactBook.hasPersonExcludingOtherPerson(person, personToExclude);
    }

    @Override
    public void deletePerson(Person target) {
        contactBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        contactBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        contactBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedContactBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public ObservableSet<Tag> getUniqueTagList() {
        return contactBook.getUniqueTagList();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return contactBook.equals(other.contactBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
