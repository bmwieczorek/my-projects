package polimorphism;

class Base {
    String info() {
        return "Base class";
    }
}

class Derived extends Base {
    String info() {
        return "Derived class";
    }
}

public class HandlerExample {
    public static void main(String[] args) {
        HandlerExample handlerExample = new HandlerExample();
        handlerExample.handle(new Base());
        handlerExample.handle(new Derived());
        handlerExample.handle((Base) new Derived());
    }

    void handle(Base b) {
        System.out.println("Base handling: " + b.info());
    }

    void handle(Derived d) {
        System.out.println("Derived handling: " + d.info());
    }
}
