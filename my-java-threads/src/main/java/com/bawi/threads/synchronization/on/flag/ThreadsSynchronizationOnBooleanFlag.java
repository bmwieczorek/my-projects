package com.bawi.threads.synchronization.on.flag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadsSynchronizationOnBooleanFlag {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsSynchronizationOnBooleanFlag.class);

    static boolean done = false;

    public static void main(String[] args) throws InterruptedException {
        LOGGER.debug("started");

        new Thread(new Runnable() {
             
            @Override
            public void run() {
                LOGGER.debug("started");
                while (!done) {}
                LOGGER.debug("stopped");
            }
        }).start();

        Thread.sleep(500); // give additional time to make sure thread-0 started already
        done = true;
        LOGGER.debug("stopped");
    }
}

/*
2015-10-22 12:19:48,794|main    |started
2015-10-22 12:19:48,794|Thread-0|started
2015-10-22 12:19:49,309|main    |stopped

Explanation:
Thread-0 still running as thread0 does not see flag change done by main thread. 
*/