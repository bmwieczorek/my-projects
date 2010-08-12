package deadlock;

import static deadlock.MyDeadlock.Pen.obtainPen;
import static deadlock.MyDeadlock.Ruller.obtainRuller;

public class MyDeadlock {
    static class Pen {
        public static synchronized void obtainPen() {
            System.out.println("Pen:" + Thread.currentThread().getName());
            sleep(100);
        }
    }

    static class Ruller {
        public static synchronized void obtainRuller() {
            System.out.println("Ruller:" + Thread.currentThread().getName());
            // sleep(50);
            obtainPen();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                obtainPen();
            }
        });
        t1.start();
        obtainRuller();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
