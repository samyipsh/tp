package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Detail;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.LinkedIn;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GITHUB = "sam--yipsh";
    private static final String INVALID_LINKEDIN = "google.com/hello";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_GITHUB = "rachwalk";
    private static final String VALID_LINKEDIN = "https://www.linkedin.com/in/rachelwalker/";
    private static final String VALID_DETAIL = "OMG!! RACHEL IS CRAZY!!!";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String EMPTY_PHONE = "";
    private static final String EMPTY_EMAIL = "";
    private static final String EMPTY_GITHUB = "";
    private static final String EMPTY_LINKEDIN = "";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }


    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parsePhone_validEmptyValue_returnsEmptyPhone() throws Exception {
        Phone emptyPhone = Phone.getEmptyPhone();
        assertEquals(emptyPhone, ParserUtil.parsePhone(EMPTY_PHONE));
    }


    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseEmail_validEmptyValue_returnsEmptyEmail() throws Exception {
        Email emptyEmail = Email.getEmptyEmail();
        assertEquals(emptyEmail, ParserUtil.parseEmail(EMPTY_EMAIL));
    }

    @Test
    public void parseGithub_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGithub((String) null));
    }

    @Test
    public void parseGithub_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGithub(INVALID_GITHUB));
    }

    @Test
    public void parseGithub_validValueWithoutWhitespace_returnsGithub() throws Exception {
        Github expectedGithub = new Github(VALID_GITHUB);
        assertEquals(expectedGithub, ParserUtil.parseGithub(VALID_GITHUB));
    }

    @Test
    public void parseGithub_validValueWithWhitespace_returnsTrimmedGithub() throws Exception {
        String githubWithWhitespace = WHITESPACE + VALID_GITHUB + WHITESPACE;
        Github expectedGithub = new Github(VALID_GITHUB);
        assertEquals(expectedGithub, ParserUtil.parseGithub(githubWithWhitespace));
    }

    @Test
    public void parseGithub_validEmptyValue_returnsEmptyGithub() throws Exception {
        Github emptyGithub = Github.getEmptyGithub();
        assertEquals(emptyGithub, ParserUtil.parseGithub(EMPTY_GITHUB));
    }

    @Test
    public void parseLinkedIn_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLinkedIn((String) null));
    }

    @Test
    public void parseLinkedIn_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLinkedIn(INVALID_LINKEDIN));
    }

    @Test
    public void parseLinkedIn_validValueWithoutWhitespace_returnsEmail() throws Exception {
        LinkedIn expectedLinkedIn = new LinkedIn(VALID_LINKEDIN);
        assertEquals(expectedLinkedIn, ParserUtil.parseLinkedIn(VALID_LINKEDIN));
    }

    @Test
    public void parseLinkedIn_validValueWithWhitespace_returnsTrimmedLinkedIn() throws Exception {
        String linkedInWithWhitespace = WHITESPACE + VALID_LINKEDIN + WHITESPACE;
        LinkedIn expectedLinkedIn = new LinkedIn(VALID_LINKEDIN);
        assertEquals(expectedLinkedIn, ParserUtil.parseLinkedIn(linkedInWithWhitespace));
    }

    @Test
    public void parseLinkedIn_validEmptyValue_returnsEmptyLinkedIn() throws Exception {
        LinkedIn emptyLinkedIn = LinkedIn.getEmptyLinkedin();
        assertEquals(emptyLinkedIn, ParserUtil.parseLinkedIn(EMPTY_LINKEDIN));
    }

    @Test
    public void parseDetail_validValueWithoutWhitespace_returnsDetail() throws Exception {
        Detail expectedDetail = new Detail(VALID_DETAIL);
        assertEquals(expectedDetail, ParserUtil.parseDetail(VALID_DETAIL));
    }

    @Test
    public void parseDetail_validValueWithWhitespace_returnsTrimmedDetail() throws Exception {
        String detailWithWhitespace = WHITESPACE + VALID_DETAIL + WHITESPACE;
        Detail expectedDetail = new Detail(VALID_DETAIL);
        assertEquals(expectedDetail, ParserUtil.parseDetail(detailWithWhitespace));
    }


    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
