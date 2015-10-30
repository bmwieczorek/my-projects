package com.bawi.threads.cpu;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParkedThread {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        lock.lock();

        new Thread(() -> {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            lock.lock();

            stopWatch.stop();
            System.out.println(stopWatch);
        }).start();

        TimeUnit.NANOSECONDS.sleep(3000000000L); // 3s
        lock.unlock();
    }
}
/*
User (app code only) time:                   0 ms (0.00%)
System (os code on behalf app e.g.I/O) time: 0 ms (0.00%)
CPU (total CPU spent for app) time:          0 ms (0.00%)
Elapsed time:                                3010 ms (100.00%)


"Thread-0" #10 prio=5 os_prio=0 tid=0x0000000058abf000 nid=0x3c0 waiting on condition [0x0000000059a4e000]
   java.lang.Thread.State: WAITING (parking)
    at sun.misc.Unsafe.park(Native Method)
    - parking to wait for  <0x00000000d5b68b70> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
    at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
    at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:836)
    at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:870)
    at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1199)
    at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:209)
    at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:285)
    at com.bawi.threads.cpu.ParkedThread.lambda$0(ParkedThread.java:17)
    at com.bawi.threads.cpu.ParkedThread$$Lambda$1/471910020.run(Unknown Source)
    at java.lang.Thread.run(Thread.java:745)

*/
