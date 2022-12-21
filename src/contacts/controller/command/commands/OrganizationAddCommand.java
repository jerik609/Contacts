package contacts.controller.command.commands;

import contacts.controller.Contacts;
import contacts.controller.command.Command;
import contacts.data.entities.Organization;
import contacts.data.entities.Person;
import contacts.input.ReadOrganization;
import contacts.input.ReadPerson;
import contacts.input.validators.NameValidator;

import java.util.Scanner;

public class OrganizationAddCommand implements Command {

    private final Contacts contacts;
    private final Organization organization;

    public OrganizationAddCommand(Contacts contacts, Scanner scanner) {
        this.contacts = contacts;
        organization = new ReadOrganization(scanner).read(new Organization.Builder(new NameValidator())).build();
    }

    @Override
    public void execute() {
        contacts.add(organization);
    }
}
