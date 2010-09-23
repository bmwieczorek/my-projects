package concurency.locks;

import static java.lang.Thread.currentThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyExecutors {

    public static void main(String[] args) {
        ExecutorService ec = Executors.newFixedThreadPool(2);
        long start = System.currentTimeMillis();
        Runnable task = new Runnable() {

            @Override
            public void run() {
                System.out.println(currentThread().getName());
                sleep(1000);
            }
        };

        for (int i = 0; i < 5; i++) {
            ec.execute(task);
        }

        ec.shutdown();
        waitTenSeconds(ec);
        long stop = System.currentTimeMillis();
        System.out.println("Took: " + (stop - start));
    }

    private static void waitTenSeconds(ExecutorService ec) {
        try {
            ec.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sleep(int value) {
        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
