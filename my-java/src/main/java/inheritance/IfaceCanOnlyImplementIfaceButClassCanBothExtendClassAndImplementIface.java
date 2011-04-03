package inheritance;

interface One {
    void myOne();
}

interface Two {
    void myTwo();
}

interface ThreeOfOneAndTwo extends One, Two {
}

abstract class ACl implements ThreeOfOneAndTwo {
    // does not have to provide actual implementation of the methods, subclass
    // then have to implement them

    // abstract class can have constructor, it will be called implicitly when
    // creating a subclass
    public ACl() {
        System.out.println("constructor of abstract class");
    }

    protected abstract void myTemplateMethod();

    // should not do that
    public void wrongUsage() {
    }

    public final void okUsage() {
        // defines a flow
    }
}

class Cl extends ACl implements One {
    @Override
    protected void myTemplateMethod() {
        // TODO Auto-generated method stub
    }

    @Override
    public void myOne() {
    }

    @Override
    public void myTwo() {
    }

}

class Claska {
    protected Claska() {
        System.out.println("claska");
    }
}

class Claska2 extends Claska {

}

public class IfaceCanOnlyImplementIfaceButClassCanBothExtendClassAndImplementIface {
    public static void main(String[] args) {
        new Cl();
    }
}
