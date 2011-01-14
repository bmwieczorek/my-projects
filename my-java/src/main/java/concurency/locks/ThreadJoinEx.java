package concurency.locks;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

public class ThreadJoinEx {

    public static void main(String[] args) {
        ThreadJoinEx tjEx = new ThreadJoinEx();
        out.println(threadName() + "starting" + sec());
        Thread sleepingThread = tjEx.createSleepingThread(5);
        sleepingThread.start();
        // sleepingThread.join();
        out.println(threadName() + "finished" + sec());

    }

    Thread createSleepingThread(final int limit) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = limit; i > 0; i--) {
                    out.println(threadName() + "sleep," + i + sec());
                    sleepSeconds(1);
                }
            }
        });
    }

    static String threadName() {
        return Thread.currentThread().getName() + ",";
    }

    static void sleepSeconds(int n) {
        try {
            Thread.sleep(1000 * n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static String sec() {
        long currentTimeMillis = currentTimeMillis();
        long value = currentTimeMillis / 1000;
        return ",second " + (value - (value / 100 * 100));
    }

}
