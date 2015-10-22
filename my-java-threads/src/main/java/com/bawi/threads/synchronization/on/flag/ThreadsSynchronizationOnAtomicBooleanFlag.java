package com.bawi.threads.synchronization.on.flag;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadsSynchronizationOnAtomicBooleanFlag {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsSynchronizationOnAtomicBooleanFlag.class);

    static AtomicBoolean done = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        LOGGER.debug("started");

        new Thread(() -> {
            LOGGER.debug("started");
            while (!done.get()) {}
            LOGGER.debug("stopped");
        }).start();

        Thread.sleep(500); // give additional time to make sure thread-0 started already
        done.set(true);
        LOGGER.debug("stopped");
    }
}
/*
2015-10-22 12:35:57,738|main    |started
2015-10-22 12:35:57,800|Thread-0|started
2015-10-22 12:35:58,315|main    |stopped
2015-10-22 12:35:58,315|Thread-0|stopped

Explanation: atomic boolean internally declares boolean as volatile 
so the thread-0 can see value change done by the main thread.
*/
