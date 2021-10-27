package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.alias.AliasTable;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private AliasTable aliasTable = new AliasTable();
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAliasTable(newUserPrefs.getAliasTable());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public AliasTable getAliasTable() {
        return aliasTable;
    }

    public void setAliasTable(AliasTable aliasTable) {
        requireNonNull(aliasTable);
        this.aliasTable = aliasTable;
    }

    /**
     * Adds alias-command pair to the alias table.
     */
    public void addAlias(String alias, String command) {
        requireNonNull(alias);
        requireNonNull(command);
        aliasTable.addAlias(alias, command);
    }

    public void deleteAlias(String alias) {
        requireNonNull(alias);
        aliasTable.deleteAlias(alias);
    }

    /**
     * Gets the existing aliases.
     */
    public Set<String> getExistingAliases() {
        return aliasTable.getExistingAliases();
    }

    public boolean containsAlias(String alias) {
        return aliasTable.containsAlias(alias);
    }

    /**
     * Gets the corresponding command for certain alias.
     */
    public String getCorrespondingCommand(String alias) {
        requireNonNull(alias);
        return aliasTable.getCorrespondingCommand(alias);
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && aliasTable.equals(o.aliasTable)
                && addressBookFilePath.equals(o.addressBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, aliasTable, addressBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nAlias table : " + aliasTable);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        return sb.toString();
    }

}
