package contacts.playground;

import contacts.input.validators.NameValidator;
import contacts.input.validators.PhoneNumberValidator;

import java.util.ArrayList;
import java.util.Arrays;

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

}
