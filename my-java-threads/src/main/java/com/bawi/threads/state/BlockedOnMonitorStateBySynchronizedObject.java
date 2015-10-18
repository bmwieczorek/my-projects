package com.bawi.threads.state;

public class BlockedOnMonitorStateBySynchronizedObject {

    public static void main(String[] args) {
        
        Object monitor = new Object();
        
        new Thread(() -> {
            System.out.println("New thread started: " + Thread.currentThread().getName());
            sleepSeconds(1); // wait so that main thread enters synchronized sleeping
            synchronized(monitor) {
                System.out.println("Thread entered synchronized, starting to sleep: " + Thread.currentThread().getName());
                sleepSeconds(5); // #2 BLOCKED (on object monitor)
                                 //  - waiting to lock <0x00000000d5b66788> (a java.lang.Object)
                System.out.println("Thread finished sleeping, leaving synchronized: " + Thread.currentThread().getName());
            }
        }).start();
        
        synchronized(monitor) {
            System.out.println("Main entered synchronized, starting to sleep: " + Thread.currentThread().getName());
            sleepSeconds(10000); // #1  TIMED_WAITING (sleeping) at java.lang.Thread.sleep(Native Method) 
                                 // - locked <0x00000000d5b66788> (a java.lang.Object)
            System.out.println("Main finished sleeping, leaving synchronized: " + Thread.currentThread().getName());
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
Entered synchronized, starting to sleep: main
New thread started: Thread-0
... (blocked)

#2
"Thread-0" #10 prio=5 os_prio=0 tid=0x00000000590c7800 nid=0x1a00 waiting for monitor entry [0x0000000059ace000]
   java.lang.Thread.State: BLOCKED (on object monitor)
    at com.bawi.threads.my.thread.pool.state.BlockedOnMonitorStateBySynchronizedObject.lambda$0(BlockedOnMonitorStateBySynchronizedObject.java:13)
    - waiting to lock <0x00000000d5b66788> (a java.lang.Object)
    at com.bawi.threads.my.thread.pool.state.BlockedOnMonitorStateBySynchronizedObject$$Lambda$1/321001045.run(Unknown Source)
    at java.lang.Thread.run(Thread.java:745)

   Locked ownable synchronizers:
    - None


#1
"main" #1 prio=5 os_prio=0 tid=0x000000000033d800 nid=0x420 waiting on condition [0x000000000208e000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
    at java.lang.Thread.sleep(Native Method)
    at com.bawi.threads.my.thread.pool.state.BlockedOnMonitorStateBySynchronizedObject.sleepSeconds(BlockedOnMonitorStateBySynchronizedObject.java:30)
    at com.bawi.threads.my.thread.pool.state.BlockedOnMonitorStateBySynchronizedObject.main(BlockedOnMonitorStateBySynchronizedObject.java:22)
    - locked <0x00000000d5b66788> (a java.lang.Object)

   Locked ownable synchronizers:
    - None


*/