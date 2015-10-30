package com.bawi.threads.cpu;

import java.util.concurrent.TimeUnit;

public class WaitingThread {

    public static void main(String[] args) throws InterruptedException {
        Object monitor = new Object();

        new Thread(() -> {
            synchronized (monitor) {
                try {
                    StopWatch stopWatch = new StopWatch();
                    stopWatch.start();

                    monitor.wait();

                    stopWatch.stop();
                    System.out.println(stopWatch);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.NANOSECONDS.sleep(3000000000L); // 3s
        synchronized(monitor) {
            monitor.notify();
        }
    }
}

/*
User (app code only) time:                   0 ms (0.00%)
System (os code on behalf app e.g.I/O) time: 0 ms (0.00%)
CPU (total CPU spent for app) time:          0 ms (0.00%)
Elapsed time:                                2998 ms (100.00%)

"Thread-0" #10 prio=5 os_prio=0 tid=0x0000000058b5b000 nid=0x162c in Object.wait() [0x00000000595ae000]
   java.lang.Thread.State: WAITING (on object monitor)
    at java.lang.Object.wait(Native Method)
    - waiting on <0x00000000d5b67a10> (a java.lang.Object)
    at java.lang.Object.wait(Object.java:502)
    at com.bawi.threads.cpu.WaitingThread.lambda$0(WaitingThread.java:16)
    - locked <0x00000000d5b67a10> (a java.lang.Object)
    at com.bawi.threads.cpu.WaitingThread$$Lambda$1/321001045.run(Unknown Source)
    at java.lang.Thread.run(Thread.java:745)

*/
