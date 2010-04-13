package test;

class MyClass {
    public final synchronized void printerr() {
        System.err.println("x");
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final synchronized void printout() {
        System.out.print("y");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadsSynchronizationTest {
    public static void main(String[] args) throws InterruptedException {
        final MyClass m1 = new MyClass();
        // final MyClass m2 = new MyClass();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (true)
                    m1.printerr();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while (true)
                    m1.printout();
                // m2.printout();
            }
        });
        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
}
