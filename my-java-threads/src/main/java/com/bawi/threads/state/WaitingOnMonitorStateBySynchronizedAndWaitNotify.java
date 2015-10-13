package com.bawi.threads.state;

public class WaitingOnMonitorStateBySynchronizedAndWaitNotify {
    
    public static void main(String[] args) {

        Object monitor = new Object();

        new Thread(() -> {
            System.out.println("Started: " + Thread.currentThread().getName());
            synchronized (monitor) {
                    try {
                        while (true) {
                            System.out.println("Before wait: " + Thread.currentThread().getName());
                            monitor.wait(); // The current thread must own this object's monitor. The thread releases ownership of this monitor and waits until another thread notifies threads waiting on this object's monitor to wake up either through a call to the notify method or the notifyAll method. The thread then waits until it can re-obtain ownership of the monitor and resumes execution. 
                                            // WAITING (on object monitor), at java.lang.Object.wait(Native Method)
                                            //  - waiting on <0x00000000d5b66428> (a java.lang.Object)
                                            //  - locked <0x00000000d5b66428> (a java.lang.Object)
                        
                            System.out.println("After wait: " + Thread.currentThread().getName());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }).start();
        
        sleepSeconds(1); // TIMED_WAITING (sleeping)
        synchronized (monitor) {
            System.out.println("Before long sleep: " + Thread.currentThread().getName());
            sleepSeconds(10000); // TIMED_WAITING (sleeping)
            System.out.println("Calling notify: " + Thread.currentThread().getName());
            monitor.notify();  
        }
        
    }
    
    public static void sleepSeconds(int sleepSeconds) {
        try {
            Thread.sleep(1000 * sleepSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


/*
Output:
Started: Thread-0
Before wait: Thread-0
Before long sleep: main
...


"Thread-0" #10 prio=5 os_prio=0 tid=0x0000000059252000 nid=0x35e0 in Object.wait() [0x0000000059dbe000]
   java.lang.Thread.State: WAITING (on object monitor)
    at java.lang.Object.wait(Native Method)
    - waiting on <0x00000000d5b66428> (a java.lang.Object)
    at java.lang.Object.wait(Object.java:502)
    at com.bawi.threads.state.WaitingOnMonitorStateBySynchronizedAndWaitNotify.lambda$0(WaitingOnMonitorStateBySynchronizedAndWaitNotify.java:12)
    - locked <0x00000000d5b66428> (a java.lang.Object)
    at com.bawi.threads.state.WaitingOnMonitorStateBySynchronizedAndWaitNotify$$Lambda$1/321001045.run(Unknown Source)
    at java.lang.Thread.run(Thread.java:745)

   Locked ownable synchronizers:
    - None


"main" #1 prio=5 os_prio=0 tid=0x000000000058e000 nid=0x23fc waiting on condition [0x00000000021bf000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
    at java.lang.Thread.sleep(Native Method)
    at com.bawi.threads.state.WaitingOnMonitorStateBySynchronizedAndWaitNotify.sleepSeconds(WaitingOnMonitorStateBySynchronizedAndWaitNotify.java:30)
    at com.bawi.threads.state.WaitingOnMonitorStateBySynchronizedAndWaitNotify.main(WaitingOnMonitorStateBySynchronizedAndWaitNotify.java:20)

   Locked ownable synchronizers:
    - None


*/