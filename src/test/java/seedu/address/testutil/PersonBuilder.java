package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Detail;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.LinkedIn;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_GITHUB = "amyio";
    public static final String DEFAULT_LINKEDIN = "https://www.linkedin.com/in/amybee/";
    public static final String DEFAULT_DETAIL = "";

    private Name name;
    private Phone phone;
    private Email email;
    private Github github;
    private LinkedIn linkedin;
    private Detail detail;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        github = new Github(DEFAULT_GITHUB);
        linkedin = new LinkedIn(DEFAULT_LINKEDIN);
        detail = new Detail(DEFAULT_DETAIL);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        github = personToCopy.getGithub();
        linkedin = personToCopy.getLinkedin();
        detail = personToCopy.getDetail();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Github} of the {@code Person} that we are building.
     */
    public PersonBuilder withGithub(String github) {
        this.github = new Github(github);
        return this;
    }

    /**
     * Sets the {@code Linkedin} of the {@code Person} that we are building.
     */
    public PersonBuilder withLinkedIn(String linkedin) {
        this.linkedin = new LinkedIn(linkedin);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Detail} of the {@code Person} that we are building.
     */
    public PersonBuilder withDetail(String detail) {
        this.detail = new Detail(detail);
        return this;
    }

    /**
     * Build person with empty phone.
     *
     * @return PersonBuilder with empty phone.
     */
    public PersonBuilder withEmptyPhone() {
        this.phone = Phone.getEmptyPhone();
        return this;
    }

    /**
     * Build person with empty email.
     *
     * @return PersonBuilder with empty email.
     */
    public PersonBuilder withEmptyEmail() {
        this.email = Email.getEmptyEmail();
        return this;
    }

    /**
     * Build person with empty Github.
     *
     * @return PersonBuilder with empty Github.
     */
    public PersonBuilder withEmptyGithub() {
        this.github = Github.getEmptyGithub();
        return this;
    }

    /**
     * Build person with empty LinkedIn.
     *
     * @return PersonBuilder with empty LinkedIn.
     */
    public PersonBuilder withEmptyLinkedin() {
        this.linkedin = LinkedIn.getEmptyLinkedin();
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, github, linkedin, detail, tags);
    }

}
