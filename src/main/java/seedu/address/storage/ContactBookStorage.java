package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ContactBook;
import seedu.address.model.ReadOnlyContactBook;

/**
 * Represents a storage for {@link ContactBook}.
 */
public interface ContactBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getContactBookFilePath();

    /**
     * Returns ContactBook data as a {@link ReadOnlyContactBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyContactBook> readContactBook() throws DataConversionException, IOException;

    /**
     * @see #getContactBookFilePath()
     */
    Optional<ReadOnlyContactBook> readContactBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyContactBook} to the storage.
     * @param contactBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveContactBook(ReadOnlyContactBook contactBook) throws IOException;

    /**
     * @see #saveContactBook(ReadOnlyContactBook)
     */
    void saveContactBook(ReadOnlyContactBook contactBook, Path filePath) throws IOException;

}
