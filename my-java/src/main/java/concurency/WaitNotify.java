package concurency;

class Block {
    // wait(), notify() must be within synchronized block
    // wait() releases the lock and thread sleeps, another thread can obtain the
    // lock to do the jobs and eventually wake up the first thread via notify()

    boolean flag = false;

    synchronized void m1(String s) {
        // System.out.println("[" + s + "] starting m1");
        System.out.println("[" + s + "] m1");
        flag = true;
        notify();
        try {
            while (true)
                wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // System.out.println("[" + s + "] stopping m1");
    }

    synchronized void a1(String s) throws InterruptedException {
        System.out.println("***");
        // flag = true;
        notify();
        // while (flag)
        wait();
    }

    synchronized void b1(String s) throws InterruptedException {
        System.out.println("---");
        // flag = false;
        notify();
        // while (!flag)
        wait();
    }

    synchronized void m2(String s) {
        // System.err.println("[" + s + "] starting m2");
        System.out.println("[" + s + "] m2");
        flag = false;
        notify();
        try {
            while (true)
                wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notify();
        // System.err.println("[" + s + "] stopping m2");
    }
}

public class WaitNotify {

    public static void main(String[] args) throws InterruptedException {
        final Thread t1;
        final Thread t2;
        final Block block = new Block();

        t1 = new Thread(new Runnable() {
            public void run() {
                // System.out.println("[t1]:starting");
                while (true) {
                    // block.m1("t1");
                    try {
                        block.a1("t1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // System.out.println("[t1]:stopping");
            };
        });

        t2 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    // System.out.println("[t2]:starting");
                    // block.m2("t2");
                    try {
                        block.b1("t2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // System.out.println("[t2]:stopping");
                }
            };
        });

        t1.start();
        Thread.sleep(3000);
        t2.start();
    }

}
