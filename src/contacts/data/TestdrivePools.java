package contacts.data;

import contacts.pool.PoolManager;
import contacts.validators.NameValidator;

public class TestdrivePools {

    public static void main(String[] args) {

        final var poolManager = new PoolManager();

        poolManager.addPool(Person.class);
        poolManager.addPool(Organization.class);

        var person = new Person.PersonBuilder(new NameValidator())
                .firstname("Michal")
                .surname("Jancok")
                .phoneNumber(PhoneNumber.buildPhoneNumber("aaa"))
                .build();

        poolManager.putValue(person);

        System.out.println(poolManager.getValue(Person.class, person.getKey()));

        var organization = new Organization("hello_world_org");
        System.out.println(organization);


    }

}
