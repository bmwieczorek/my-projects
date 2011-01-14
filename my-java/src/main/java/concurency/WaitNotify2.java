package concurency;

class Block2 {
    // wait(), notify() must be within synchronized block
    // wait() releases the lock and thread sleeps, another thread can obtain the
    // lock to do the jobs and eventually wake up the first thread via notify()

    boolean flag = true;

    synchronized void a() throws InterruptedException {
        System.out.println("***before while");
        while (flag) {
            System.out.println("***in while before wait");
            wait();
            System.out.println("***in while after wait");
        }
        System.out.println("***after while before notify");
        flag = true;
        notify();
        System.out.println("***after notify");
    }

    synchronized void b() throws InterruptedException {
        System.out.println("---before while");
        while (!flag) {
            System.out.println("---in while before wait");
            wait();
            System.out.println("---in while after wait");
        }
        System.out.println("---after while and before notify");
        flag = false;
        notify();
        System.out.println("---after notify");

    }
}

public class WaitNotify2 {

    public static void main(String[] args) throws InterruptedException {
        final Block2 block = new Block2();
        createThreadA(block).start();
        Thread.sleep(3000);
        createThreadB(block).start();
    }

    private static Thread createThreadB(final Block2 block) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    block.b();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static Thread createThreadA(final Block2 block) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    block.a();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
