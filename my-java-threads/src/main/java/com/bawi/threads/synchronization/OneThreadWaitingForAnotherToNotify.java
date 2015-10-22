package com.bawi.threads.synchronization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OneThreadWaitingForAnotherToNotify {

    private static final Logger LOGGER = LoggerFactory.getLogger(OneThreadWaitingForAnotherToNotify.class);

    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
        LOGGER.debug("Started");
        Thread thread = new Thread(() -> {
            LOGGER.debug("Started");
            sleepMillis(1000); // additional sleep time to show that main thread will also need to wait that time
            synchronized (MONITOR) {
                MONITOR.notify();
            }
            LOGGER.debug("Finished");
        });
        thread.start();
        synchronized (MONITOR) {
            MONITOR.wait();
        }
        LOGGER.debug("Finished");
    }

    public static synchronized void sleep1() {
        sleepMillis(1000);
    }

    public static synchronized void sleep2() {
        sleepMillis(1000);
    }

    public static void sleepMillis(int millis) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/*
2015-10-19 17:49:14,447|main    |Started
2015-10-19 17:49:14,498|Thread-0|Started

2015-10-19 17:49:15,498|Thread-0|Finished
2015-10-19 17:49:15,498|main    |Finished

*/