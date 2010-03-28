package concurency;

class Block2 {
    // wait(), notify() must be within synchronized block
    // wait() releases the lock and thread sleeps, another thread can obtain the
    // lock to do the jobs and eventually wake up the first thread via notify()

    boolean flag = true;

    synchronized void a1(String s) throws InterruptedException {
        System.out.println("***before wait");
        while (flag)
            wait();
        System.out.println("***after wait and before notify");
        flag = true;
        notify();
        System.out.println("***after notify");
    }

    synchronized void b1(String s) throws InterruptedException {
        System.out.println("---before wait");
        while (!flag)
            wait();
        System.out.println("---after wait and before notify");
        flag = false;
        notify();
        System.out.println("---after notify");
        
    }
}

public class WaitNotify2 {

    public static void main(String[] args) throws InterruptedException {
        final Thread t1;
        final Thread t2;
        final Block2 block = new Block2();

        t1 = new Thread(new Runnable() {
            public void run() {
                while (true)
                    try {
                        block.a1("t1");
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            };
        });

        t2 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        block.b1("t2");
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        });

        t1.start();
        Thread.sleep(3000);
        t2.start();
    }

}
