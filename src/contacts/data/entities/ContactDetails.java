package contacts.data.entities;

import contacts.data.attributes.Address;
import contacts.data.attributes.PhoneNumber;
import contacts.common.pool.Keyed;

public abstract class ContactDetails extends Keyed {
    protected PhoneNumber phoneNumber;
    protected Address address;

    public ContactDetails(Address address, PhoneNumber phoneNumber) {
        super();
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
