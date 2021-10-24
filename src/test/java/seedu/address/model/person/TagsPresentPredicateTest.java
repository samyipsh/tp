package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagsPresentPredicateTest {

    @Test
    public void test1() {
    }

    @Test
    public void equals() {
        List<String> firstTag = Collections.singletonList("first");
        List<String> secondTag = Collections.singletonList("second");

        TagsPresentPredicate firstPredicate = new TagsPresentPredicate(firstTag);
        TagsPresentPredicate secondPredicate = new TagsPresentPredicate(secondTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsPresentPredicate firstPredicateCopy = new TagsPresentPredicate(firstTag);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

    }

    @Test
    public void test_tagPresent_returnsTrue() {
        // Normal Tag
        TagsPresentPredicate predicate = new TagsPresentPredicate(Collections.singletonList("friend"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("friend").build()));

        // Tag with mixed-case
        predicate = new TagsPresentPredicate(Collections.singletonList("fRieNd"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("friend").build()));
    }

    @Test
    public void test_tagNotPresent_returnsFalse() {
        TagsPresentPredicate predicate = new TagsPresentPredicate(Collections.singletonList("friend"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

}
