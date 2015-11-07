package com.bawi.interrupt;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterruptBusyThread {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterruptBusyThread.class);

    public static void main(String[] args) throws InterruptedException {
        LOGGER.debug("Started");

        Thread thread = new Thread(() -> {
            LOGGER.debug("Started");
            for (long counter = 0; counter < 99999999999L; counter++) { // busy loop
            }
            LOGGER.debug("Finished, thread isInterrupted:" + Thread.currentThread().isInterrupted());
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        LOGGER.debug("Finished sleeping, interrupting busy thread ...");
        thread.interrupt();
        LOGGER.debug("Finished");
    }
}
/*
Output:
2015-11-07 10:30:13,380|main                            |Started
2015-11-07 10:30:13,446|Thread-0                        |Started
2015-11-07 10:30:14,447|main                            |Finished sleeping, interrupting busy thread ...
2015-11-07 10:30:14,447|main                            |Finished
2015-11-07 10:30:52,958|Thread-0                        |Finished, thread isInterrupted:true

Note the call thread.interrupt() did not make any effect apart from setting interrupted flag. 
Thread kept on running. 

*/