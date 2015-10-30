package com.bawi.threads.cpu;

public class InfiniteLoop {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (long i = 0L; i < 9999999999L; i++) { // 6s
         // Thread running 100%, taking 1/(n cores) of JVM/System overall CPU
        }

        stopWatch.stop();
        System.out.println(stopWatch);
    }

}

/*
User (app code only) time:                   3166 ms (99.44%)
System (os code on behalf app e.g.I/O) time: 0 ms (0.00%)
CPU (total CPU spent for app) time:          3166 ms (99.44%)
Elapsed time:                                3184 ms (100.00%)

"main" #1 prio=5 os_prio=0 tid=0x0000000001c9e000 nid=0x1e4c runnable [0x000000000241f000]
   java.lang.Thread.State: RUNNABLE
    at com.bawi.threads.cpu.InfiniteLoop.main(InfiniteLoop.java:9)

*/
