package com.bawi.threads.volatilehappensbefore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VolatileHappensBeforeGuarantee {

    private static final Logger LOGGER = LoggerFactory.getLogger(VolatileHappensBeforeGuarantee.class);

    static boolean nonVolatileDone = false;
    //static boolean volatileDone = false;
    static volatile boolean volatileDone = false; //

    public static void main(String[] args) {
        LOGGER.debug("started");

        new Thread(new Runnable() {
             
            @Override
            public void run() {
                LOGGER.debug("started");
                while (!nonVolatileDone) { // note that synchronization is still made on non-volatile flag but volatile  
                                           // flag makes the non-volatile flag seen eventually as described below

                    @SuppressWarnings("unused")
                    boolean done = volatileDone; // volatile read makes also all subsequent non-volatile reads from RAM 
                                                 // so non-volatile read is also from RAM

                }
                LOGGER.debug("stopped");
            }
        }).start();

        sleepMillis(100); // give additional time to make sure thread-0 started already

        nonVolatileDone = true;
        volatileDone = true; // volatile write makes all preceding writes (so nonVolatileDone = true as well) 
                             // also to be written to RAM

        LOGGER.debug("stopped");
    }

    public static void sleepMillis(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
2015-10-22 14:08:54,401|main    |started
2015-10-22 14:08:54,401|Thread-0|started
2015-10-22 14:08:54,510|main    |stopped
2015-10-22 14:08:54,510|Thread-0|stopped

but if we remove volatile then
2015-10-22 14:09:59,055|main    |started
2015-10-22 14:09:59,055|Thread-0|started
2015-10-22 14:09:59,164|main    |stopped
(with Thread-0 still running)

Explanation:
Volatile happens-before guarantee makes non-volatile variables write before volatile to be flushed to RAM as well. 
Then as long as another thread read volatile variable (from RAM) then it will also see changes to the non-volatile variables. 
*/