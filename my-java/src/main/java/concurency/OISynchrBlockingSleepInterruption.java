package concurency;

import java.io.IOException;

class Car {
    void m(String s) {
        System.out.println("[" + s + "] starting m");
        System.out.println("[" + s + "] stopping m");
    }

    synchronized void m1(String s) {
        System.out.println("[" + s + "] starting m1");
        try {
            // blocking on i/o cannot be interrupted, interrupt message is
            // ignored, no need to catch it
            System.in.read();
        } catch (IOException e) {
            System.out.println("[" + s + "]" + e.getMessage());
        }
        System.out.println("[" + s + "] stopping m1");
    }

    synchronized void m2(String s) {
        System.out.println("[" + s + "] starting m2");
        try {
            // can be interrupted via t.interrupt();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("[" + s + "]" + e.getMessage());
        }
        System.out.println("[" + s + "] stopping m2");
    }

}

public class OISynchrBlockingSleepInterruption {

    public static void main(String[] args) throws InterruptedException {

        final Car car1 = new Car();
        final Thread t1;
        final Thread t2;

        t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("[t2]:starting");
                // t1.interrupt();
                car1.m1("t2");
                car1.m("t2");
                System.out.println("[t2]:stopping");
            };
        });

        t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("[t1]:starting");
                //
                t2.interrupt();
                car1.m2("t1");
                System.out.println("[t1]:stopping");
            };
        });

        // t1.start();
        t2.start();
        Thread.sleep(1000);
        t1.start();
    }

}
