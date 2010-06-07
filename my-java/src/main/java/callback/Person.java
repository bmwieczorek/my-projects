package callback;

public class Person {
    String name;
    int age;

    Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "name=" + name + ",age=" + age;
    }
}