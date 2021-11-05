package seedu.address.testutil;

import seedu.address.model.ContactBook;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code ContactBook ab = new ContactBookBuilder().withPerson("John", "Doe").build();}
 */
public class ContactBookBuilder {

    private ContactBook addressBook;

    public ContactBookBuilder() {
        addressBook = new ContactBook();
    }

    public ContactBookBuilder(ContactBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code ContactBook} that we are building.
     */
    public ContactBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public ContactBook build() {
        return addressBook;
    }
}
