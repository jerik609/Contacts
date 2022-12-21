package contacts.data.attributes;

public class Address {
    private String address;

    private Address(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                '}';
    }

    public static Address buildAddress(String address) {
        return new Address(address);
    }
}
