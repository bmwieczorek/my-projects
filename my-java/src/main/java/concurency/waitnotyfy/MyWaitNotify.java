package concurency.waitnotyfy;

abstract class Operation implements Runnable {
    final MyWaitNotify mwn;

    public Operation(MyWaitNotify mwn) {
        this.mwn = mwn;
    }

    protected abstract void doInIndefiniteLoop() throws InterruptedException;

    @Override
    public void run() {
        try {
            while (true)
                doInIndefiniteLoop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Plus extends Operation {

    public Plus(MyWaitNotify mwn) {
        super(mwn);
    }

    @Override
    protected void doInIndefiniteLoop() throws InterruptedException {
        mwn.plus();
    }
}

class Minus extends Operation {
    public Minus(MyWaitNotify mwn) {
        super(mwn);
    }

    @Override
    protected void doInIndefiniteLoop() throws InterruptedException {
        mwn.minus();
    }
}

public class MyWaitNotify {
    boolean flag;

    synchronized void plus() throws InterruptedException {
        System.out.print("+");
        flag = true;
        notify();
        while (flag) {
            wait();
        }
    }

    synchronized void minus() throws InterruptedException {
        System.out.print("-");
        flag = false;
        notify();
        while (!flag) {
            wait();
        }
    }

    public static void main(String[] args) {
        final MyWaitNotify mwn = new MyWaitNotify();
        // ExecutorService exec = Executors.newCachedThreadPool();
        // exec.execute(new Plus(mwn));
        // exec.execute(new Minus(mwn));

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        mwn.plus();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        mwn.minus();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
