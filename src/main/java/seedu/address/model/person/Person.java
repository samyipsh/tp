package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the contact book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final LinkedIn linkedin;

    // Data fields
    private final Github github;
    private final Detail detail;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Github github,
                  LinkedIn linkedin, Detail detail, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.linkedin = linkedin;
        this.github = github;
        this.detail = detail;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Github getGithub() {
        return github;
    }

    public LinkedIn getLinkedin() {
        return this.linkedin;
    }

    public Detail getDetail() {
        return detail;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name and at least one
     * unique field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        if (otherPerson == null) {
            return false;
        }

        if (!hasSameName(otherPerson)) {
            return false;
        }

        if (hasEmptyUniqueFields() && otherPerson.hasEmptyUniqueFields()) {
            return true;
        }

        return hasSameUniqueField(otherPerson);
    }

    /**
     * Returns true if both persons names are similar.
     *
     * @param otherPerson Person object to compare to.
     * @return Whether the two Person objects have a similar Name object.
     */
    public boolean hasSameName(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        if (otherPerson == null) {
            return false;
        }

        return otherPerson.getName().isSameName(getName());
    }

    /**
     * Returns true if both Person objects have at least one
     * field unique to people that are the same.
     *
     * @param otherPerson Person object to compare to.
     * @return Whether the two Person objects have a similar unique field.
     */
    public boolean hasSameUniqueField(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        if (otherPerson == null) {
            return false;
        }

        return otherPerson.getEmail().isSameEmail(getEmail())
                || otherPerson.getGithub().isSameGithub(getGithub())
                || otherPerson.getLinkedin().isSameLinkedIn(getLinkedin())
                || otherPerson.getPhone().isSamePhone(getPhone());
    }

    /**
     * Returns whether this Person object has only
     * empty unique fields.
     *
     * @return whether this Person object has only empty unique fields.
     */
    public boolean hasEmptyUniqueFields() {
        return getEmail().isEmpty()
                && getGithub().isEmpty()
                && getLinkedin().isEmpty()
                && getPhone().isEmpty();
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getLinkedin().equals(getLinkedin())
                && otherPerson.getGithub().equals(getGithub())
                && otherPerson.getDetail().equals(getDetail())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, github, linkedin, detail, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; LinkedIn: ")
                .append(getLinkedin())
                .append("; Github: ")
                .append(getGithub())
                .append("; Detail: ")
                .append(getDetail());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }


}
