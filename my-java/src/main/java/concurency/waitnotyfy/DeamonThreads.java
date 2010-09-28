package concurency.waitnotyfy;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.lang.Thread.currentThread;

public class DeamonThreads {

    public static void main(String[] args) {
        System.out.println(threadName() + " started");
        Thread sleepingThread = sleepingThread();
        sleepingThread.setDaemon(false);
        sleepingThread.start();
        System.out.println(threadName() + " finished");
    }

    private static Thread sleepingThread() {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                out.println(threadName() + " started");
                sleepSeconds(5);
                out.println(threadName() + " finished");
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
        return second() + ":" + currentThread().getName() + ":";
    }

    private static String second() {
        long currentTimeMillis = currentTimeMillis();
        long value = currentTimeMillis / 1000;
        return "Second " + (value - (value / 100 * 100));
    }
}
