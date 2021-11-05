package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ContactBook;
import seedu.address.model.ReadOnlyContactBook;
import seedu.address.model.person.Detail;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.LinkedIn;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ContactBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Github("alexio"),
                    new LinkedIn("https://www.linkedin.com/in/alex-yeoh-b9502683/"),
                    new Detail("Y1 CS"), getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Github("bernio"),
                    new LinkedIn("https://www.linkedin.com/in/bernice-yu/"),
                    new Detail("Y2 CS"), getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Github("charlio"),
                    new LinkedIn("https://www.linkedin.com/in/charlotte-oliverio-4a553440/"),
                    new Detail("Y1 BIZ"), getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Github("davidio"),
                    new LinkedIn("https://www.linkedin.com/in/david-li-58a14049/"),
                    new Detail(""), getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Github("irfanio"),
                    new LinkedIn("https://www.linkedin.com/in/irfan-ibrahim-709a5452/"),
                    new Detail("Y4 CEG"), getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Github("royio"),
                    new LinkedIn("https://www.linkedin.com/in/roy-ramakrishnan-balakrishnan-b7293818b/"),
                    new Detail("Y3 CS"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyContactBook getSampleContactBook() {
        ContactBook sampleAb = new ContactBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
