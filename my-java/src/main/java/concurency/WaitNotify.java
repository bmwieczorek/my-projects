package concurency;

class Block {
    // wait(), notify() must be within synchronized block
    // wait() releases the lock and thread sleeps, another thread can obtain the
    // lock to do the jobs and eventually wake up the first thread via notify()

    boolean flag = false;

    synchronized void a1() throws InterruptedException {
        System.out.println("***");
        notify();
        wait();
    }

    synchronized void b1() throws InterruptedException {
        System.out.println("---");
        // flag = false;
        notify();
        // while (!flag)
        wait();
    }
}

public class WaitNotify {

    public static void main(String[] args) throws InterruptedException {
        final Thread t1;
        final Thread t2;
        final Block block = new Block();

        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // System.out.println("[t1]:starting");
                while (true) {
                    // block.m1("t1");
                    try {
                        block.a1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // System.out.println("[t1]:stopping");
            }
        });

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // System.out.println("[t2]:starting");
                    // block.m2("t2");
                    try {
                        block.b1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // System.out.println("[t2]:stopping");
                }
            }
        });

        t1.start();
        Thread.sleep(3000);
        t2.start();
    }

}
