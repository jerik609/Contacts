package contacts.data.entities;

import contacts.data.attributes.Address;
import contacts.data.attributes.PhoneNumber;
import contacts.pool.Keyed;

public abstract class ContactDetails extends Keyed {
    protected PhoneNumber phoneNumber;
    protected Address address;

    public ContactDetails(Address address, PhoneNumber phoneNumber) {
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

}
