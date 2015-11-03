package com.bawi.threads.cpu;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class MonitorAllCoresCpuLoad {

    private static volatile boolean hasAnyThreadFinished = false;

    public static void main(String[] args) throws Exception {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        int threadsCount = Runtime.getRuntime().availableProcessors();
        CyclicBarrier barrier = new CyclicBarrier(threadsCount + 1); // + 1 to include main thread measuring CPU load
        for (int i = 0; i < threadsCount; i++) {
            createAndStartThread(barrier);
        }
        barrier.await();
        System.out.println("All threads started");
        while (!hasAnyThreadFinished) {
            getCpuLoad(operatingSystemMXBean);
            TimeUnit.MILLISECONDS.sleep(100);
        }
        System.out.println("One of thread finished");
    }

    private static void createAndStartThread(CyclicBarrier cyclicBarrier) {
        new Thread(() -> {
            try {
                cyclicBarrier.await();
                for (long i = 0L; i < 19999999999L; i++) { // 6s
                    // Thread running 100%, taking 1/(n cores) of JVM/System overall CPU
                }
                hasAnyThreadFinished = true;
                System.out.println(Thread.currentThread().getName() + " finished");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    private static void getCpuLoad(OperatingSystemMXBean mxBean) {
        for (Method method : mxBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            String methodName = method.getName();
            if (methodName.startsWith("get") && methodName.contains("Cpu") && methodName.contains("Load")
                    && Modifier.isPublic(method.getModifiers())) {

                Object value;
                try {
                    value = method.invoke(mxBean);
                } catch (Exception e) {
                    value = e;
                }
                System.out.println(methodName + " = " + value);
            }
        }
        System.out.println("");
    }
}
/*
Busy loop: all cores busy
getProcessCpuLoad = 0.9968879402893512
getSystemCpuLoad = 1.0

Busy loop: one of four cores busy
getProcessCpuLoad = 0.24999679493343077
getSystemCpuLoad = 0.2571523807692343

Sleeping loop: 
getProcessCpuLoad = 0.0 
getSystemCpuLoad = 0.014311079773084212
 */
