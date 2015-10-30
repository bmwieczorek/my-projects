package com.bawi.threads.cpu;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

public class StopWatch {

    private final ThreadMXBean bean;
    private long startCpuTime;
    private long startUserTime;
    private long startNano;
    private long stopCpuTime;
    private long stopUserTime;
    private long stopNano;

    public StopWatch() {
        bean = ManagementFactory.getThreadMXBean();
    }

    public void start() {
        startCpuTime = bean.getCurrentThreadCpuTime();
        startUserTime = bean.getCurrentThreadUserTime();
        startNano = System.nanoTime();
    }

    public void stop() {
        stopCpuTime = bean.getCurrentThreadCpuTime();
        stopUserTime = bean.getCurrentThreadUserTime();
        stopNano = System.nanoTime();
    }

    @Override
    public String toString() {
        long elapsedNano = stopNano - startNano;
        long cpuTime = stopCpuTime - startCpuTime;
        long userTime = stopUserTime - startUserTime;
        long systemTime = cpuTime - userTime;
        return 
            String.format("User (app code only) time:                   %d ms (%,.2f%%)\n", TimeUnit.NANOSECONDS.toMillis(userTime), (double)userTime/elapsedNano * 100) +
            String.format("System (os code on behalf app e.g.I/O) time: %d ms (%,.2f%%)\n", TimeUnit.NANOSECONDS.toMillis(systemTime), (double)systemTime/elapsedNano * 100) +
            String.format("CPU (total CPU spent for app) time:          %d ms (%,.2f%%)\n", TimeUnit.NANOSECONDS.toMillis(cpuTime),  (double)cpuTime/elapsedNano * 100) +
            String.format("Elapsed time:                                %d ms (%,.2f%%)\n", TimeUnit.NANOSECONDS.toMillis(elapsedNano), 100d);
    }

}
