package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tags} matches Tags given.
 */
public class TagsPresentPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagsPresentPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Set<Tag> tagSet = person.getTags();
        StringJoiner sj = new StringJoiner(" ", "", "");
        for (Tag elem : tagSet) {
            sj.add(elem.tagName);
        }
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(sj.toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsPresentPredicate // instanceof handles nulls
                && keywords.equals(((TagsPresentPredicate) other).keywords)); // state check
    }
}
