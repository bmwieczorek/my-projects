package com.bawi.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadlockThreads {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeadlockThreads.class);

    static class Thread0 extends Thread {

        @Override
        public void run() {
            LOGGER.debug("run started");
            synchOnThread0Class();
            LOGGER.debug("run stopped");
        }
    };

    public static void main(String[] args) {
        LOGGER.debug("started");
        new Thread0().start();
        synchOnMainClass();
        LOGGER.debug("stopped");
    }

    static synchronized void synchOnMainClass() { // is synchronized implicitly DeadlockThreads.class
        LOGGER.debug("entered synchOnMainClass");
        sleep(10); // sleep until the other thread also enters synchronized method
        LOGGER.debug("before synchOnThread0Class");
        synchOnThread0Class(); // try to access synchronized method already taken by another thread
        LOGGER.debug("after synchOnThread0Class");
    }

    static void synchOnThread0Class() {
        synchronized (Thread0.class) { // methods are synchronized on different locks so each thread can INITIALLY enter
            LOGGER.debug("entered synchOnThread0Class");
            DeadlockThreads.sleep(15); // sleep 
            LOGGER.debug("before synchOnMainClass");
            DeadlockThreads.synchOnMainClass();
            LOGGER.debug("after synchOnMainClass");
        }
    }

    static void sleep(int sleepMillis) {
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Two threads (main, and Thread0) are started and they immediately enter synchronized blocks
// having different locks (on DeadlockThreads.class and Thread.class).
// After some sleep time one of the threads while still being in the synchronized block
// (and keeping its lock) tries to call another synchronized method associated to different
// lock that is in initially taken by another thread. That another thread after finishing
// sleeping in the synchronized method calls the other synchronized method (associated to the
// lock that is owned by another thread). As a result both of the threads wait on each other.

/*
2015-10-19 17:58:18,045|main    |started
2015-10-19 17:58:18,046|main    |entered synchOnMainClass
2015-10-19 17:58:18,047|Thread-0|run started
2015-10-19 17:58:18,047|Thread-0|entered synchOnThread0Class
2015-10-19 17:58:18,057|main    |before synchOnThread0Class
2015-10-19 17:58:18,062|Thread-0|before synchOnMainClass
*/