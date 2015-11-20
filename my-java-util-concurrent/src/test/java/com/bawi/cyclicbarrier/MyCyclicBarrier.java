package com.bawi.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class MyCyclicBarrier {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(currentThreadId() + Thread.currentThread().getName() + "|Started");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        TimeUnit.SECONDS.sleep(10);

        Thread t0 = createNewThread(cyclicBarrier);
        t0.start();

        TimeUnit.SECONDS.sleep(2);

        Thread t1 = createNewThread(cyclicBarrier);
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread t2 = createNewThread(cyclicBarrier);
        t2.start();

        t0.join();
        t1.join();
        t2.join();

        System.out.println(currentThreadId() + Thread.currentThread().getName() + "|Stopped");
    }

    private static Thread createNewThread(CyclicBarrier cyclicBarrier) {
        Thread thread = new Thread(() -> {
            try {
                cyclicBarrier.await();
                System.out.println(currentThreadId() + Thread.currentThread().getName() + "|Started");
                for (long l = 0L; l < 9999999999999L; l++) {
                }
                System.out.println(currentThreadId() + Thread.currentThread().getName() + "|Stopped");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return thread;
    }

    private static String currentThreadId() {
        long id = Thread.currentThread().getId();
        return String.format("Thread id=%-2s|", id);
    }
}

/*
2015-11-03 08:16:00,562|Thread-2  |Started
2015-11-03 08:16:00,563|Thread-1  |Started
2015-11-03 08:16:00,563|Thread-0  |Started

All print threads started printing at the same time even if the threads were started at different moments
*/