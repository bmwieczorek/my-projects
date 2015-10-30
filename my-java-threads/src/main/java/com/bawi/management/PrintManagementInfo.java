package com.bawi.management;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class PrintManagementInfo {
    public static void main(String[] args) {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        getMBeanInformation(operatingSystemMXBean);

        System.out.println("====");

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        System.out.println("getCurrentThreadCpuTime = " + threadMXBean.getCurrentThreadCpuTime());
        System.out.println("getCurrentThreadUserTime = " + threadMXBean.getCurrentThreadUserTime());
        System.out.println("getThreadCount = " + threadMXBean.getThreadCount());
        System.out.println("getTotalStartedThreadCount = " + threadMXBean.getTotalStartedThreadCount());
        System.out.println("getDaemonThreadCount = " + threadMXBean.getDaemonThreadCount());
        System.out.println("getPeakThreadCount = " + threadMXBean.getPeakThreadCount());
        System.out.println("isCurrentThreadCpuTimeSupported = " + threadMXBean.isCurrentThreadCpuTimeSupported());
        System.out.println("isObjectMonitorUsageSupported = " + threadMXBean.isObjectMonitorUsageSupported());
        System.out.println("isThreadCpuTimeEnabled = " + threadMXBean.isThreadCpuTimeEnabled());
        System.out.println("isThreadCpuTimeSupported = " + threadMXBean.isThreadCpuTimeSupported());
        System.out.println("isSynchronizerUsageSupported = " + threadMXBean.isSynchronizerUsageSupported());
        System.out.println("isThreadContentionMonitoringEnabled = " + threadMXBean.isThreadContentionMonitoringEnabled());
        System.out.println("isThreadContentionMonitoringSupported = " + threadMXBean.isThreadContentionMonitoringSupported());

        for (long threadId : threadMXBean.getAllThreadIds()) {
            System.out.println("Thread id = " + threadId + ": getThreadCpuTime " + threadMXBean.getThreadCpuTime(threadId));
            System.out.println("Thread id = " + threadId + ": getThreadUserTime " + threadMXBean.getThreadUserTime(threadId));
            System.out.println("Thread id = " + threadId + ": getThreadInfo.getThreadName " + threadMXBean.getThreadInfo(threadId).getThreadName());
            System.out.println("Thread id = " + threadId + ": getThreadInfo.getWaitedTime " + threadMXBean.getThreadInfo(threadId).getWaitedTime());
            System.out.println("Thread id = " + threadId + ": getThreadInfo.getBlockedTime " + threadMXBean.getThreadInfo(threadId).getBlockedTime());
            System.out.println("Thread id = " + threadId + ": getThreadInfo.getLockInfo " + threadMXBean.getThreadInfo(threadId).getLockInfo());

            System.out.println("Thread id = " + threadId + ": getThreadInfo " + threadMXBean.getThreadInfo(threadId));
            System.out.println("Thread id = " + threadId + ": getThreadInfo " + threadMXBean.getThreadInfo(threadId, 100));
        }
    }

    private static void getMBeanInformation(Object bean) {
        System.out.println(bean);
        for (Method method : bean.getClass().getDeclaredMethods()) {
          method.setAccessible(true);
          if (method.getName().startsWith("get") && Modifier.isPublic(method.getModifiers())) {
              Object value;
              try {
                  value = method.invoke(bean);
              } catch (Exception e) {
                  value = e;
              } 
              System.out.println(method.getName() + " = " + value);
          }
        }
    }
}
