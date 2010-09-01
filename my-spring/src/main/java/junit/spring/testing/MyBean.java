package junit.spring.testing;

public class MyBean {

    MyBean() {
        System.err.println("Created MyBean !!!");
    }

    public String sayHello() {
        String text = "Hello";
        System.out.println(text);
        return text;
    }

    public String sayGoodbye() {
        String text = "Goodbye";
        System.out.println(text);
        return text;
    }

}
