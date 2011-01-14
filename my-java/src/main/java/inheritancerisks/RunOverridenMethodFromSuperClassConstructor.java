package inheritancerisks;

class Super {
    void myPrint() {
        System.out.println("superclass my print overridden by subclass");
    }

    public Super() {
        System.out.println("superclass constructor");

        // will be printed before subclass constructor is executed, will use the
        // override method from superclass, not initialized name variable
        myPrint();
    }
}

class Sub extends Super {
    String name;

    public Sub() {
        System.out.println("subclass constructor");
        name = "bawi";
    }

    @Override
    void myPrint() {
        System.out.println("subclass my print:" + name);
    }
}

public class RunOverridenMethodFromSuperClassConstructor {
    public static void main(String[] args) {
        Sub sub = new Sub();
        System.out.println("***Superclass object initialized***");
        sub.myPrint();
    }
}
