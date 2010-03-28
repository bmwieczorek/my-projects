package concurency;

class BlockLevel {
    // synchronization on method level uses the same lock. So if one thread has
    // already acquired the lock, another thread is blocked waiting for the lock
    // to be released to acquire it

    // block level synchronization enables you to use multiple different locks
    // so threads do not block each other

    Object xLock = new Object();

    Object yLock = new Object();

    void m(String s) {
        System.out.println("[" + s + "] starting m");
        System.out.println("[" + s + "] stopping m");
    }

    void m1(String s) {
        System.out.println("[" + s + "] starting m1");
        synchronized (xLock) {
            System.out.println("[" + s + "] synchronized m1");
        }
        System.out.println("[" + s + "] stopping m1");
    }

    void m2(String s) {
        System.out.println("[" + s + "] starting m2");
        synchronized (yLock) {
            System.out.println("[" + s + "] in synchronized m2");
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(1000);
                    System.out.print(".");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[" + s + "] out synchronized m2");
        }
        System.out.println("[" + s + "] stopping m2");
    }

}

public class BlockVsMethodLock {

    public static void main(String[] args) throws InterruptedException {

        final BlockLevel bl = new BlockLevel();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("[t1]:starting");
                bl.m2("t1");
                System.out.println("[t1]:stopping");
            };
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("[t2]:starting");
                bl.m1("t2");
                System.out.println("[t2]:stopping");
            };
        });

        t1.start();
        Thread.sleep(1000);
        t2.start();

    }

}
