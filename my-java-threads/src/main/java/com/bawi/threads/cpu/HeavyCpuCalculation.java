package com.bawi.threads.cpu;

import java.math.BigDecimal;

public class HeavyCpuCalculation {

    public static void main(String[] args) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (int j = 0; j < 10000; j++) { // 5s
            BigDecimal n = BigDecimal.ONE;
            BigDecimal i = BigDecimal.ONE;
            BigDecimal max = BigDecimal.valueOf(1600L);
            while (i.compareTo(max) <= 0) {
                n = n.multiply(i);
                i = i.add(BigDecimal.ONE);
            }
        }

        stopWatch.stop();
        System.out.println(stopWatch);
    }

}
/*
User (app code only) time:                   5304 ms (97.01%)
System (os code on behalf app e.g.I/O) time: 93 ms (1.71%)
CPU (total CPU spent for app) time:          5397 ms (98.72%)
Elapsed time:                                5467 ms (100.00%)

"main" #1 prio=5 os_prio=0 tid=0x000000000213d800 nid=0x1078 runnable [0x00000000023ef000]
   java.lang.Thread.State: RUNNABLE
    at com.bawi.threads.cpu.HeavyCpuCalculation.main(HeavyCpuCalculation.java:17)

*/