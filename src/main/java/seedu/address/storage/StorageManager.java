package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyContactBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ContactBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ContactBookStorage contactBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ContactBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ContactBookStorage contactBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.contactBookStorage = contactBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ContactBook methods ==============================

    @Override
    public Path getContactBookFilePath() {
        return contactBookStorage.getContactBookFilePath();
    }

    @Override
    public Optional<ReadOnlyContactBook> readContactBook() throws DataConversionException, IOException {
        return readContactBook(contactBookStorage.getContactBookFilePath());
    }

    @Override
    public Optional<ReadOnlyContactBook> readContactBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return contactBookStorage.readContactBook(filePath);
    }

    @Override
    public void saveContactBook(ReadOnlyContactBook contactBook) throws IOException {
        saveContactBook(contactBook, contactBookStorage.getContactBookFilePath());
    }

    @Override
    public void saveContactBook(ReadOnlyContactBook contactBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        contactBookStorage.saveContactBook(contactBook, filePath);
    }

}
