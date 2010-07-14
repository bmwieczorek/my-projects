package refactoring;

public class Clazz2 implements Iface2 {

    int getAge() {
        return 28;
    }

    @Override
    public void method2() {
        System.out.println("m2:" + getAge());
    }

}
