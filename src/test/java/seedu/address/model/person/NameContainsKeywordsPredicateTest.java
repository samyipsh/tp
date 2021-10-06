package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameAndTagsContainKeywordsPredicate firstPredicate = new NameAndTagsContainKeywordsPredicate(
                firstPredicateKeywordList);
        NameAndTagsContainKeywordsPredicate secondPredicate = new NameAndTagsContainKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameAndTagsContainKeywordsPredicate firstPredicateCopy = new NameAndTagsContainKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameAndTagsContainKeywordsPredicate predicate = new NameAndTagsContainKeywordsPredicate(Collections
                .singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameAndTagsContainKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameAndTagsContainKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameAndTagsContainKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_tagsContainKeywords_returnsTrue() {
        // One keyword
        NameAndTagsContainKeywordsPredicate predicate = new NameAndTagsContainKeywordsPredicate(Collections
                .singletonList("programmer"));
        assertTrue(predicate.test(new PersonBuilder().withTags("programmer").build()));

        // Multiple keywords
        predicate = new NameAndTagsContainKeywordsPredicate(Arrays.asList("programmer", "designer"));
        assertTrue(predicate.test(new PersonBuilder().withTags("programmer", "designer").build()));

        // Only one matching keyword
        predicate = new NameAndTagsContainKeywordsPredicate(Arrays.asList("programmer", "designer"));
        assertTrue(predicate.test(new PersonBuilder().withTags("designer", "tester").build()));

        // Mixed-case keywords
        predicate = new NameAndTagsContainKeywordsPredicate(Arrays.asList("pROGrammer", "DESigner"));
        assertTrue(predicate.test(new PersonBuilder().withTags("programmer", "designer").build()));
    }

    @Test
    public void test_nameAndTagsDoNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameAndTagsContainKeywordsPredicate predicate = new NameAndTagsContainKeywordsPredicate(Collections
                .emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags("programmer").build()));

        // Non-matching keyword
        predicate = new NameAndTagsContainKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("programmer").build()));

        // Keywords match phone and email but does not match name or tags
        predicate = new NameAndTagsContainKeywordsPredicate(Arrays
                .asList("12345", "alice@email.com", "Alicio"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withTags("programmer")
                .withGithub("Alicio").build()));
    }
}
