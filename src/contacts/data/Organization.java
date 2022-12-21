package contacts.data;

public class Organization extends ContactDetails {
    private String name;

    public Organization(String name) {
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
}
