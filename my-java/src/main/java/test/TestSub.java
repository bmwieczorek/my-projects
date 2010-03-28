package test;

class SuperClass {
    private void printIt() {
        System.out.println("SuperClass");
    }

    void printIt(boolean f) {
        if (f) {
            System.out.println("Super-part 2");
        } else {
            printIt();
        }
    }
}

class SubClass extends SuperClass {
    void printIt() {
        System.out.println("SubClass");
    }
}

public class TestSub {
    public static void main(String args[]) {
        SubClass sc = new SubClass();
        sc.printIt();
        sc.printIt(false);
    }
}