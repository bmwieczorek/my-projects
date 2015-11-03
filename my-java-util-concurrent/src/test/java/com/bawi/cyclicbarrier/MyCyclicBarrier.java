package com.bawi.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCyclicBarrier {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyCyclicBarrier.class);

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        createAndStartNewThread(cyclicBarrier);
        TimeUnit.SECONDS.sleep(2);
        createAndStartNewThread(cyclicBarrier);
        TimeUnit.SECONDS.sleep(1);
        createAndStartNewThread(cyclicBarrier);
    }

    private static void createAndStartNewThread(CyclicBarrier cyclicBarrier) {
        Thread thread = new Thread(() -> {
            try {
                cyclicBarrier.await();
                LOGGER.debug("Started");
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
/*
2015-11-03 08:16:00,562|Thread-2  |Started
2015-11-03 08:16:00,563|Thread-1  |Started
2015-11-03 08:16:00,563|Thread-0  |Started

All print threads started at the same time even if the threads were started at different moments
*/