package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyContactBook;

/**
 * A class to access ContactBook data stored as a json file on the hard disk.
 */
public class JsonContactBookStorage implements ContactBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonContactBookStorage.class);

    private Path filePath;

    public JsonContactBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getContactBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyContactBook> readContactBook() throws DataConversionException {
        return readContactBook(filePath);
    }

    /**
     * Similar to {@link #readContactBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyContactBook> readContactBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableContactBook> jsonContactBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableContactBook.class);
        if (!jsonContactBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonContactBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveContactBook(ReadOnlyContactBook contactBook) throws IOException {
        saveContactBook(contactBook, filePath);
    }

    /**
     * Similar to {@link #saveContactBook(ReadOnlyContactBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveContactBook(ReadOnlyContactBook contactBook, Path filePath) throws IOException {
        requireNonNull(contactBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableContactBook(contactBook), filePath);
    }

}
