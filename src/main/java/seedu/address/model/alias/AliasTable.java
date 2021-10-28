package seedu.address.model.alias;

import java.util.HashMap;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

/**
 * Stores the alias-command pairs.
 */
public class AliasTable {

    private ObservableMap<String, String> aliasTable = FXCollections.observableHashMap();

    public void addAlias(String alias, String command) {
        aliasTable.put(alias, command);
    }

    public Set<String> getExistingAliases() {
        return aliasTable.keySet();
    }

    public String getCorrespondingCommand(String alias) {
        // Possible enhancement by creating custom exception when {@code alias} does not exist in the table
        return aliasTable.get(alias);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AliasTable // instanceof handles nulls
                && aliasTable.equals(((AliasTable) other).aliasTable)); // state check
    }

    @Override
    public int hashCode() {
        return aliasTable.hashCode();
    }

    @Override
    public String toString() {
        return "Alias table: " + aliasTable.toString();
    }
}
