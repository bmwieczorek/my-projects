package concurency;

import java.util.concurrent.TimeUnit;

public class StopThread {
    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                System.out.println("started other thread");
                while (!stopRequested) {
                    i++;
                    // System.out.println("running other thread");
                }
                System.out.println("finished other thread");
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
        System.out.println("finished main");
    }
}
