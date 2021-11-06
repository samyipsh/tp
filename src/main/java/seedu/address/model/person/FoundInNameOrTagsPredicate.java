package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} and Tags matches any of the keywords given.
 */
public class FoundInNameOrTagsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public FoundInNameOrTagsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        String name = person.getName().fullName;

        Set<Tag> tagSet = person.getTags();

        StringJoiner sj = new StringJoiner(" ", "", "");

        sj.add(name);

        for (Tag elem : tagSet) {
            sj.add(elem.tagName);
        }

        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(sj.toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FoundInNameOrTagsPredicate // instanceof handles nulls
                && keywords.equals(((FoundInNameOrTagsPredicate) other).keywords)); // state check
    }

}
