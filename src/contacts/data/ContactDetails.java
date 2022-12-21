package contacts.data;

import contacts.pool.Keyed;

public abstract class ContactDetails extends Keyed {
    protected PhoneNumber phoneNumber;
    protected Address address;

    ContactDetails(Address address, PhoneNumber phoneNumber) {
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

}
