package refactoring;

public class Clazz implements Interface {

    Clazz2 clazz2;

    private String name;

    @Override
    public void method1() {
        System.out.println("m1:" + name);
    }

}
