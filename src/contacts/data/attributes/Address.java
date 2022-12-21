package contacts.data.attributes;

public class Address {
    private String address;

    private Address(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return address;
    }

    public static Address buildAddress(String address) {
        return new Address(address);
    }
}
