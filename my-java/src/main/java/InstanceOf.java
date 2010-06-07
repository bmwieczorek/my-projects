interface Person {
}

class Parent implements Person {
}

class Child extends Parent {
}

public class InstanceOf {

    public static void main(String[] args) {
        Person parent = new Parent();
        Person child = new Child();
        System.out.println((parent instanceof Person));
        System.out.println((child instanceof Person));
        System.out.println((parent instanceof Parent));
        System.out.println((child instanceof Parent));

        // superclass is not an instance of subclass
        System.out.println((parent instanceof Child));
        System.out.println((child instanceof Child));

    }

}
