package com.bawi.interrupt;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterruptThreadWaitingCallingWaitMethod {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterruptThreadWaitingCallingWaitMethod.class);

    public static void main(String[] args) throws InterruptedException {
        Object monitor = new Object();

        LOGGER.debug("Started");
        Thread thread = new Thread(() -> {
            LOGGER.debug("Started, about to enter synchronized block");

            synchronized (monitor) {
                LOGGER.debug("Entered synchronized block and sleeping 3 seconds");
                try {
                    sleepSeconds(3); // sleep 3s to show that wait() released the lock for main thread
                    LOGGER.debug("Finished sleeping, about to call wait() that releases monitor lock");
                    monitor.wait(); // calling wait() must be in synchronized block otherwise 
                                    // java.lang.IllegalMonitorStateException will be thrown

                                    // wait() immediately releases the lock so other thread could enter synchronized 
                                    // block ON THE SAME MONITOR lock and could send notify on that monitor

                                    // current thread need wait to re-acquire the lock to execute next 
                                    // instruction in the synchronized block
                } catch (Exception e) {
                    LOGGER.warn("Re-acquired monitor lock and logging exception in catch:", e);
                }
                LOGGER.debug("Leaving synchronized block");
            }

            LOGGER.debug("Finished");
        });
        thread.start();

        sleepSeconds(1); //wait so that thread0 access to monitor first
        LOGGER.debug("Finished sleeping, about to enter synchronized block");
        synchronized (monitor) {
            LOGGER.debug("Entered synchronized block, interrupting thread waiting on monitor");
            thread.interrupt();
            LOGGER.debug("Sleeping while holding the monitor lock so Thread0 remains calling wait()");
            sleepSeconds(3); 
            LOGGER.debug("Finished sleeping and leaving synchronized block");
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
2015-11-07 16:21:49,697|main                            |Started
2015-11-07 16:21:49,748|Thread-0                        |Started, about to enter synchronized block
2015-11-07 16:21:49,748|Thread-0                        |Entered synchronized block and sleeping 3 seconds

2015-11-07 16:21:50,749|main                            |Finished sleeping, about to enter synchronized block

2015-11-07 16:21:52,749|Thread-0                        |Finished sleeping, about to call wait() that releases monitor lock
2015-11-07 16:21:52,749|main                            |Entered synchronized block, interrupting thread waiting on monitor
2015-11-07 16:21:52,749|main                            |Sleeping while holding the monitor lock so Thread0 remains calling wait()

2015-11-07 16:21:55,749|main                            |Finished sleeping and leaving synchronized block
2015-11-07 16:21:55,749|main                            |Finished
2015-11-07 16:21:55,750|Thread-0                        |Re-acquired monitor lock and logging exception in catch:
java.lang.InterruptedException
    at java.lang.Object.wait(Native Method)
    at java.lang.Object.wait(Object.java:502)
    at com.bawi.interrupt.InterruptThreadWaitingCallingWaitMethod.lambda$0(InterruptThreadWaitingCallingWaitMethod.java:24)
    at java.lang.Thread.run(Thread.java:745)
2015-11-07 16:21:55,752|Thread-0                        |Leaving synchronized block
2015-11-07 16:21:55,752|Thread-0                        |Finished

Note that thread0 fist accessed the monitor for synchronized block and sleeps 3 seconds to show that main thread 
needs to wait for the monitor. When thread0 finished sleeping then it calls monitor.wait() method that internally 
releases the monitor lock and puts the thread0 into WAITING state (waiting for monitor.notify() or 
monitor.notifyAll()). Thread0 is now disabled from CPU scheduling. As the monitor lock is released then main 
thread enters synchronized block and interrupts the thread0 so that threads0 is no longer waiting and proceeds 
to catch clause and next instructions. 
*/