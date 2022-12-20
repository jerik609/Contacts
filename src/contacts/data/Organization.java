package contacts.data;

import contacts.pool.Keyed;

public class Organization extends ContactDetails implements Keyed {
    private String name;

    @Override
    public String getKey() {
        return null;
    }
}
