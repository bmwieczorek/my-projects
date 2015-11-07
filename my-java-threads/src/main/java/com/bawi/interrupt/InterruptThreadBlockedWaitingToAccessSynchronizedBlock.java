package com.bawi.interrupt;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterruptThreadBlockedWaitingToAccessSynchronizedBlock {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterruptThreadBlockedWaitingToAccessSynchronizedBlock.class);

    public static void main(String[] args) throws InterruptedException {
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
            sleepSeconds(3); // wait 3s to make sure thread0 gets BLOCKED waiting on monitor to synchronized block
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

Note that interrupting Thread-0 BLOCKED waiting on monitor to get access to synchronized block

"Thread-2" #16 prio=5 os_prio=0 tid=0x0000000058b5d000 nid=0x1294 waiting for monitor entry [0x000000005acbf000]
   java.lang.Thread.State: BLOCKED (on object monitor)
    at com.bawi.interrupt.InterruptThreadBlockedWaitingToAccessSynchronizedBlock.lambda$0(InterruptThreadBlockedWaitingToAccessSynchronizedBlock.java:23)
    - waiting to lock <0x00000000d5f942b8> (a java.lang.Object)
    at com.bawi.interrupt.InterruptThreadBlockedWaitingToAccessSynchronizedBlock$$Lambda$3/2046562095.run(Unknown Source)
    at java.lang.Thread.run(Thread.java:745)

does not give any effect apart from only changing thread isInterrupted flag. The Thread-0 keeps on being BLOCKED 
waiting to access monitor.
*/