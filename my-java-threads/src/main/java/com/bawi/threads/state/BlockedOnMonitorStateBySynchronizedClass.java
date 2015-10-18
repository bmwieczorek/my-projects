package com.bawi.threads.state;

public class BlockedOnMonitorStateBySynchronizedClass {

    public static void main(String[] args) {
        
        new Thread(() -> {
            System.out.println("New thread started: " + Thread.currentThread().getName());
            sleepSeconds(1); // wait so that main thread enters synchronized sleeping in m1
            synchronizedSleepSeconds(5); // #2 BLOCKED (on object monitor)
                                         //  - waiting to lock <0x00000000d5b657d0> (a java.lang.Class for com.bawi.threads.my.thread.pool.state.BlockedOnMonitorStateBySynchronizedClass)

        }).start();
        
        synchronizedSleepSeconds(10000); // #1  TIMED_WAITING (sleeping) at java.lang.Thread.sleep(Native Method) 
                                         // - locked <0x00000000d5b657d0> (a java.lang.Class for com.bawi.threads.my.thread.pool.state.BlockedOnMonitorStateBySynchronizedClass)
    }
    
    public synchronized static void synchronizedSleepSeconds(int sleepSeconds) {
        System.out.println("Entered synchronized, starting to sleep: " + Thread.currentThread().getName());
        sleepSeconds(sleepSeconds);
        System.out.println("Finished sleeping, leaving synchronized: " + Thread.currentThread().getName());
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
Entered synchronized, starting to sleep: main
New thread started: Thread-0
... (blocked)

#1 
"main" #1 prio=5 os_prio=0 tid=0x0000000001d4d800 nid=0x226c waiting on condition [0x00000000020af000]
        java.lang.Thread.State: TIMED_WAITING (sleeping)
         at java.lang.Thread.sleep(Native Method)
         at com.bawi.threads.my.thread.pool.state.BlockedOnMonitorStateBySynchronizedClass.sleepSeconds(BlockedOnMonitorStateBySynchronizedClass.java:25)
         at com.bawi.threads.my.thread.pool.state.BlockedOnMonitorStateBySynchronizedClass.synchronizedSleepSeconds(BlockedOnMonitorStateBySynchronizedClass.java:18)
         - locked <0x00000000d5b657d0> (a java.lang.Class for com.bawi.threads.my.thread.pool.state.BlockedOnMonitorStateBySynchronizedClass)
         at com.bawi.threads.my.thread.pool.state.BlockedOnMonitorStateBySynchronizedClass.main(BlockedOnMonitorStateBySynchronizedClass.java:12)

        Locked ownable synchronizers:
         - None

#2
"Thread-0" #10 prio=5 os_prio=0 tid=0x0000000058e03800 nid=0x11dc waiting for monitor entry [0x00000000592ce000]
   java.lang.Thread.State: BLOCKED (on object monitor)
    at com.bawi.threads.my.thread.pool.state.BlockedOnMonitorStateBySynchronizedClass.synchronizedSleepSeconds(BlockedOnMonitorStateBySynchronizedClass.java:17)
    - waiting to lock <0x00000000d5b657d0> (a java.lang.Class for com.bawi.threads.my.thread.pool.state.BlockedOnMonitorStateBySynchronizedClass)
    at com.bawi.threads.my.thread.pool.state.BlockedOnMonitorStateBySynchronizedClass.lambda$0(BlockedOnMonitorStateBySynchronizedClass.java:9)
    at com.bawi.threads.my.thread.pool.state.BlockedOnMonitorStateBySynchronizedClass$$Lambda$1/791452441.run(Unknown Source)
    at java.lang.Thread.run(Thread.java:745)

   Locked ownable synchronizers:
    - None


*/