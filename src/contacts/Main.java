package contacts;

import contacts.data.Person;
import contacts.input.Input;
import contacts.input.MenuAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final Input input = new Input(scanner);
        final Contacts contacts = new Contacts();

        final Person.PersonBuilder personBuilder = new Person.PersonBuilder();

        var menuAction = MenuAction.EXIT;
        do {
            System.out.println("Enter action (add, remove, edit, count, list, exit):");
            menuAction = MenuAction.translateToMenuAction(scanner.nextLine());

            switch (menuAction) {
                case COUNT -> System.out.println("count");
                case EDIT -> System.out.println("edit");
                case REMOVE -> System.out.println("remove");
                case ADD -> {
                    System.out.println("add");
                    Person person = input.readPerson(personBuilder).build();
                    contacts.addPerson(person);
                    System.out.println("A Phone Book with a single record created!");
                }
                case LIST -> {
                    System.out.println("list");
                    contacts.displayPhoneBook();
                }
            }
        } while (menuAction != MenuAction.EXIT);
    }
}
