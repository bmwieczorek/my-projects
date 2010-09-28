package concurency;

import static java.lang.Thread.currentThread;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayAccess {
    final static List<Integer> ints = new CopyOnWriteArrayList<Integer>();
    static {
        ints.add(1);
        ints.add(2);
        ints.add(3);
    }

    public static void main(String[] args) {
        iteratingThread().start();
        ints.remove(2);
        for (Integer j : ints) {
            System.err.println(threadName() + j);
        }
    }

    private static Thread iteratingThread() {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                for (Integer i : ints) {
                    System.out.println(threadName() + i);
                    sleepSeconds(1);
                }
            }
        });
    }

    private static void sleepSeconds(int n) {
        try {
            Thread.sleep(1000 * n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String threadName() {
        return currentThread().getName() + ":";
    }

}
