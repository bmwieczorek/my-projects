package inheritance;

class SuperCl {
	private void printIt() {
		// void printIt() {
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

class SubCl extends SuperCl {
    void printIt() {
		System.out.println("SubClass");
    }
}

public class InheritanceTest {
    public static void main(String args[]) {
		SubCl sc = new SubCl();
        sc.printIt();
        sc.printIt(false);
    }
}