package passbyvalueorreference;

import java.util.ArrayList;
import java.util.List;

public class JavaPassByReferenceValue {

    public static class Person {
        private String name;
        public Person() {
            System.out.println("Created: " + this);
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return "Person [name=" + name + "]";
        }
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("Bartek");
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        System.out.println("Persons=" + persons); // [Bartek]

        List<Person> persons2 = new ArrayList<>(persons); // makes a copy of all values of references to Person objects 

        person.setName("Kazek"); // internal state modification of Person object
        System.out.println("Persons=" + persons); // [Kazek] persons contain person object which internal state was modified
        System.out.println("Persons2=" + persons2); // [Kazek] persons2 contain person object which internal state was modified

        persons.remove(0); // persons loses value of reference to person
        System.out.println("Persons=" + persons);  // [] 
        System.out.println("Persons2=" + persons2); // [Kazek]  person2 has still value of reference to person object
    }
}
