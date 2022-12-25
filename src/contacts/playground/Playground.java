package contacts.playground;

import contacts.controller.Contacts;
import contacts.controller.newmenu.Menu;
import contacts.common.tree.Node;
import contacts.common.tree.Tree;
import contacts.input.validators.NameValidator;
import contacts.input.validators.PhoneNumberValidator;
import contacts.common.pool.Keyed;

import java.util.*;

public class Playground {

    public static class LabRat implements Cloneable {

        private ArrayList<String> props = new ArrayList<>();

        public LabRat(String... props) {
            this.props.addAll(Arrays.asList(props));
        }

        public void addProp(String prop) {
            this.props.add(prop);
        }

        public void setProps(ArrayList<String> props) {
            this.props = props;
        }

        @Override
        public LabRat clone() {
            try {
                var newRat = (LabRat) super.clone();
                newRat.setProps(new ArrayList<>());
                newRat.addProp("untouched");
                return newRat;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }

        @Override
        public String toString() {
            return "LabRat{" +
                    "props=" + props +
                    '}';
        }
    }

    public static void main1(String[] args) {
        var x = new NameValidator();

        Object y = new NameValidator();
        Object bad = new PhoneNumberValidator();

        var z = x.getClass().cast(y);


        System.out.println(z.validate("ahoj"));

        try {
            var crash = x.getClass().cast(bad);
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }

        var rat = new LabRat("brown", "hungry");
        var blah = rat.clone();
        System.out.println("BlahRat before: " + blah);
        rat.addProp("ratFunky");
        System.out.println("BlahRat after: " + blah);
    }

    static class ListingItem {
        private final String displayName;
        private final String key;
        private final Class<? extends Keyed> type;

        ListingItem(String displayName, String key, Class<? extends Keyed> type) {
            this.displayName = displayName;
            this.key = key;
            this.type = type;
        }
    }

    public static void main(String[] args) {
        var tree = new Tree<>(Node.createRootNode("animal", "animal"));

        var root = tree.getRoot();

        var nodeMammals = Node.createInternalNode("mammals", "mammals", root);
        var nodeLizards = Node.createInternalNode("lizards", "lizards", root);
        var nodeInsects = Node.createInternalNode("insects", "insects", root);

        var nodeHuman = Node.createInternalNode("humans", "humans", nodeMammals);

        System.out.println("whatever");
    }

    public static void main2(String[] args) {
        final var scanner = new Scanner(System.in);
        final var contacts = new Contacts(scanner);




        var menu = new Menu(scanner, contacts);

        menu.run();


//        var root = Node.createRootNode("menu");
//
//        var nodeAdd = new Node<>("add", root);
//        var nodeList = new Node<>("list", root);
//        var nodeSearch = new Node<>("search", root);
//        var nodeCount = new Node<>("count", root);
//        var nodeExit = new Node<>("exit", root);

    }

}
