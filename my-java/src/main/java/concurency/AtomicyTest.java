package concurency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicyTest implements Runnable {
    private int i = 0;

    public int getValue() {
        return i;
    }

    private synchronized void evenIncrement() throws InterruptedException {
        i++;
        i++;
    }

    public void run() {
        while (true)
            try {
                evenIncrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicyTest at = new AtomicyTest();
        exec.execute(at);
        while (true) {
            int val = at.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }

}
