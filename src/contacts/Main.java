package contacts;

import contacts.data.Person;
import contacts.input.Input;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Input input = new Input(scanner);
        Contacts contacts = new Contacts();
        Person.PersonBuilder personBuilder = new Person.PersonBuilder();

        Person person = input.readPerson(personBuilder).build();
        contacts.addPerson(person);

        // debug
        //contacts.displayPhoneBook();

        System.out.println("A Phone Book with a single record created!");
    }
}
