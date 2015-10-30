package com.bawi.threads.cpu;

import java.util.concurrent.TimeUnit;

public class SleepingThread {

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        TimeUnit.NANOSECONDS.sleep(3000000000L); // 3s

        stopWatch.stop();
        System.out.println(stopWatch);
    }
}
/*
User (app code only) time:                   0 ms (0.00%)
System (os code on behalf app e.g.I/O) time: 0 ms (0.00%)
CPU (total CPU spent for app) time:          0 ms (0.00%)
Elapsed time:                                3000 ms (100.00%)

"main" #1 prio=5 os_prio=0 tid=0x0000000001c5e000 nid=0x16dc waiting on condition [0x00000000025af000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
    at java.lang.Thread.sleep(Native Method)
    at java.lang.Thread.sleep(Thread.java:340)
    at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
    at com.bawi.threads.cpu.SleepingThread.main(SleepingThread.java:11)


*/
