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

    @Override
    public String toString() {
        return "name=" + name + ",age=" + age;
    }
}