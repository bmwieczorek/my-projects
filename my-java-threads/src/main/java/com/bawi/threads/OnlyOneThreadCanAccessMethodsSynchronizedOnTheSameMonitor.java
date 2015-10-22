package com.bawi.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnlyOneThreadCanAccessMethodsSynchronizedOnTheSameMonitor {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OnlyOneThreadCanAccessMethodsSynchronizedOnTheSameMonitor.class);
    
    public static void main(String[] args) throws InterruptedException {
        LOGGER.debug("Started");
        
        Thread t1 = new Thread(() -> {
            LOGGER.debug("Started");
            
            // we explicitly sleep 10 ms in main thread for thread t1 to enter synchronized method sychronizedSleep1 first
            sychronizedSleep1(3000);
            LOGGER.debug("Finished");
        });
        t1.start();
        sleepMillis(10); // wait 10 ms so thread t1 enters synchronized sychronizedSleep1 method first

        /* Main thread cannot enter synchronizedSleep2 as thread t1 is already in synchronized method sychronizedSleep1
           that owns the same shared monitor (OnlyOneThreadCanAccessMethodsSynchronizedOnTheSameMonitor.class) as synchronizedSleep2. 
           So main thread needs to wait 3s till thread t1 leave sychronizedSleep1.
           After that time main thread can eventually enter synchronizedSleep2. 
        */

        synchronizedSleep2(2000); 
        LOGGER.debug("Finished");
    }

    public static synchronized void sychronizedSleep1(int sleepMillis) {
        LOGGER.debug("synchronized sleep1");
        sleepMillis(sleepMillis);
    }

    public static synchronized void synchronizedSleep2(int sleepMillis) {
        LOGGER.debug("synchronized sleep2");
        sleepMillis(sleepMillis);
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
2015-10-19 17:35:23,158|main    |Started
2015-10-19 17:35:23,210|Thread-0|Started
2015-10-19 17:35:23,210|Thread-0|synchronized sleep1

2015-10-19 17:35:26,210|Thread-0|Finished
2015-10-19 17:35:26,210|main    |synchronized sleep2

2015-10-19 17:35:28,210|main    |Finished
*/
