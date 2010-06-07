package reflection;

interface MyInterface {
    void myMethod(String s);
}

class MyClass implements MyInterface {
    public void myMethod(String s) {
        System.out.println("AA " + s + " BB");
    }
}

class MyClassProxy implements MyInterface {
    MyInterface classToProxy;

    public MyClassProxy(MyInterface classToProxy) {
        this.classToProxy = classToProxy;
    }

    public void myMethod(String s) {
        System.out.println("xxxBeforexxx");
        classToProxy.myMethod(s + "xxxInsidexxx");
        System.out.println("xxxAfterxxx");
    }
}

public class SimpleProxy {

    static void execute(MyInterface myInterface) {
        myInterface.myMethod("ania");
    }

    public static void main(String[] args) {
        MyInterface real = new MyClass();
        execute(real);
        MyInterface proxy = new MyClassProxy(real);
        execute(proxy);
    }
}
