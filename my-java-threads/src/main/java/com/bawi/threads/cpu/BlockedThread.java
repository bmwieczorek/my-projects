package com.bawi.threads.cpu;

import java.util.concurrent.TimeUnit;

public class BlockedThread {

    public static void main(String[] args) throws InterruptedException {
        Object monitor = new Object();

        new Thread(() -> {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            synchronized (monitor) {
            }

            stopWatch.stop();
            System.out.println(stopWatch);
        }).start();

        synchronized (monitor) {
            TimeUnit.NANOSECONDS.sleep(5000000000L); // 5s
        }
    }
}
/*
User (app code only) time:                   0 ms (0.00%)
System (os code on behalf app e.g.I/O) time: 0 ms (0.00%)
CPU (total CPU spent for app) time:          0 ms (0.00%)
Elapsed time:                                4993 ms (100.00%)

"Thread-0" #10 prio=5 os_prio=0 tid=0x0000000058f8c800 nid=0x1ddc waiting for monitor entry [0x0000000059d1e000]
   java.lang.Thread.State: BLOCKED (on object monitor)
    at com.bawi.threads.cpu.BlockedThread.lambda$0(BlockedThread.java:14)
    - waiting to lock <0x00000000d5b675e8> (a java.lang.Object)
    at com.bawi.threads.cpu.BlockedThread$$Lambda$1/321001045.run(Unknown Source)
    at java.lang.Thread.run(Thread.java:745)
*/
