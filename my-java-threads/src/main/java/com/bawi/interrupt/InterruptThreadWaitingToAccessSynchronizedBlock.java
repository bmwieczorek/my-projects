package com.bawi.interrupt;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterruptThreadWaitingToAccessSynchronizedBlock {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterruptThreadWaitingToAccessSynchronizedBlock.class);

    public static void main(String[] args) throws InterruptedException {
// 3
        Object monitor = new Object();

        LOGGER.debug("Started");
        Thread thread = new Thread(() -> {
            LOGGER.debug("Started, about to sleep");
            sleepSeconds(1); //wait so that main thread get access to monitor first
            LOGGER.debug("Finished sleeping, about to enter synchronized block");

            synchronized (monitor) {
                LOGGER.debug("Entered synchronized block, thread isInterrupted:{}", Thread.currentThread().isInterrupted());
            }

            LOGGER.debug("Finished");
        });
        thread.start();

        synchronized (monitor) {
            LOGGER.debug("Entered synchronized block, started sleeping");
            sleepSeconds(3); // wait 3s to make sure thread0 starts waiting on monitor 
            LOGGER.debug("Interrupting thread waiting on monitor");
            thread.interrupt();
            sleepSeconds(3);
            LOGGER.debug("Leaving synchronized block");
        }

        LOGGER.debug("Finished");
    }

    private static void sleepSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
Output:
2015-11-07 10:47:29,337|main                            |Started
2015-11-07 10:47:29,403|main                            |Entered synchronized block, started sleeping
2015-11-07 10:47:29,403|Thread-0                        |Started, about to sleep

2015-11-07 10:47:30,404|Thread-0                        |Finished sleeping, about to enter synchronized block

2015-11-07 10:47:32,404|main                            |Interrupting thread waiting on monitor

2015-11-07 10:47:35,404|main                            |Leaving synchronized block
2015-11-07 10:47:35,404|main                            |Finished
2015-11-07 10:47:35,404|Thread-0                        |Entered synchronized block, thread isInterrupted:true
2015-11-07 10:47:35,404|Thread-0                        |Finished

Note that interrupting thread waiting on monitor to access synchronized block does not give any effect apart from 
only changing thread isInterrupted flag. The thread keeps on waiting to access monitor.
*/