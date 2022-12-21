package contacts.data;

public class Organization extends ContactDetails {
    private final String name;

    private Organization(String name, Address address, PhoneNumber phoneNumber) {
        super(address, phoneNumber);
        this.name = name;
    }

    @Override
    protected String getKeyInternal() {
        return name;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                "key='" + super.getKey() + '\'' +
                '}';
    }

    public static class Builder {
        private String name;
        private Address address;
        private PhoneNumber phoneNumber;

        public Builder() {

        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder phoneNumber(PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder from(Organization other) {
            this.name = other.name;
            this.address = other.address;
            this.phoneNumber = other.phoneNumber; // we can do this, phoneNumber is immutable
            return this;
        }

        public Organization build() {
            return new Organization(name, address, phoneNumber);
        }

    }
}
