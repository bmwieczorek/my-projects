package com.bawi.threads.state;

public class WaitingOnMonitorByWaitInThreadJoin {

    public static void main(String[] args) {

        Thread t0 = new Thread(() -> {
            System.out.println("Started: " + Thread.currentThread().getName());
            sleepSeconds(1000);
            System.out.println("Finished: " + Thread.currentThread().getName());
        });
        t0.start();

        sleepSeconds(1); 
        try {
            t0.join(); // Waits for this thread to die  (here main thread waits for the t0 thread to die)  
                       // WAITING (on object monitor), at java.lang.Object.wait(Native Method)
                       // - waiting on <0x00000000d5b664e0> (a java.lang.Thread)
                       // at java.lang.Thread.join(Thread.java:1245)
                       // - locked <0x00000000d5b664e0> (a java.lang.Thread)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished: " + Thread.currentThread().getName());
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
...
Finished: Thread-0
Finished: main

"Thread-0" #10 prio=5 os_prio=0 tid=0x0000000059132000 nid=0x24c8 waiting on condition [0x00000000598ee000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
    at java.lang.Thread.sleep(Native Method)
    at com.bawi.threads.my.thread.pool.state.WaitingOnMonitorByWaitInThreadJoin.sleepSeconds(WaitingOnMonitorByWaitInThreadJoin.java:26)
    at com.bawi.threads.my.thread.pool.state.WaitingOnMonitorByWaitInThreadJoin.lambda$0(WaitingOnMonitorByWaitInThreadJoin.java:9)
    at com.bawi.threads.my.thread.pool.state.WaitingOnMonitorByWaitInThreadJoin$$Lambda$1/791452441.run(Unknown Source)
    at java.lang.Thread.run(Thread.java:745)

   Locked ownable synchronizers:
    - None


"main" #1 prio=5 os_prio=0 tid=0x000000000013e000 nid=0x2ed8 in Object.wait() [0x0000000001f8f000]
   java.lang.Thread.State: WAITING (on object monitor)
    at java.lang.Object.wait(Native Method)
    - waiting on <0x00000000d5b664e0> (a java.lang.Thread)
    at java.lang.Thread.join(Thread.java:1245)
    - locked <0x00000000d5b664e0> (a java.lang.Thread)
    at java.lang.Thread.join(Thread.java:1319)
    at com.bawi.threads.my.thread.pool.state.WaitingOnMonitorByWaitInThreadJoin.main(WaitingOnMonitorByWaitInThreadJoin.java:16)

   Locked ownable synchronizers:
    - None

*/
    
