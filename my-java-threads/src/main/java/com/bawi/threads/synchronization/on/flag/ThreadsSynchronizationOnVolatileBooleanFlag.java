package com.bawi.threads.synchronization.on.flag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadsSynchronizationOnVolatileBooleanFlag {
    private static final Logger LOGGER 
        = LoggerFactory.getLogger(ThreadsSynchronizationOnVolatileBooleanFlag.class);

    static volatile boolean done = false;

    public static void main(String[] args) throws InterruptedException {
        LOGGER.debug("started");

        new Thread(new Runnable() {

            @Override
            public void run() {
                LOGGER.debug("started");
                while (!done) {
                }
                LOGGER.debug("stopped");
            }
        }).start();

        Thread.sleep(500); // give additional time to make sure thread-0 started already
        done = true;
        LOGGER.debug("stopped");
    }
}
/*
2015-10-22 12:24:18,369|main    |started
2015-10-22 12:24:18,369|Thread-0|started
2015-10-22 12:24:18,883|main    |stopped
2015-10-22 12:24:18,883|Thread-0|stopped

Explanation:
volatile flag makes all threads read/write directly into RAM main memory. 
so the thread-0 can see value change done by the main thread
*/
