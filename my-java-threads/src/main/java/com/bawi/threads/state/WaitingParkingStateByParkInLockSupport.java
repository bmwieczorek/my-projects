package com.bawi.threads.state;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitingParkingStateByParkInLockSupport {
    
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
    
        System.out.println("Main before lock called: " + Thread.currentThread().getName());
        lock.lock(); // acquire a lock and proceed
        System.out.println("Main after lock called: " + Thread.currentThread().getName());

        new Thread(() -> {
            System.out.println("New thread before calling lock: " + Thread.currentThread().getName());
            try {
                // lock.lock(); // WAITING (parking), 
                                // - parking to wait for  <0x00000000d5b7b6b0> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
                                // Acquires the lock. If the lock is not available then the current thread becomes disabled for thread scheduling purposes and lies dormant until the lock has been acquired. 
                lock.tryLock(100, TimeUnit.SECONDS); // TIMED_WAITING (parking), 
                                                     // Acquires the lock if it is free within the given waiting time.
                                                     // If the lock is available this method returns immediately with the value true. 
                                                     // If the lock is not available then the current thread becomes disabled for thread scheduling purposes and lies dormant until one of three things happens: 
                                                        // •The lock is acquired by the current thread; or 
                                                        // •Some other thread interrupts the current thread, and interruption of lock acquisition is supported; or 
                                                        // •The specified waiting time elapses 
                                                     // If the specified waiting time elapses then the value false is returned.            
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("New thread after calling lock: " + Thread.currentThread().getName());
            
        }).start();

        System.out.println("Main before sleep: " + Thread.currentThread().getName());
        
        Thread.sleep(1000000); //    TIMED_WAITING (sleeping), Locked ownable synchronizers: - <0x00000000d5b7b6b0> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
        
        System.out.println("Main after sleep: " + Thread.currentThread().getName());
        System.out.println("Main before lock released: " + Thread.currentThread().getName());
        lock.unlock();
        System.out.println("Main after lock released: " + Thread.currentThread().getName());
    }
}

/*

Output:
Main before lock called: main
Main after lock called: main
Main before sleep: main
New thread before calling lock: Thread-0
... (blocked)


"main" #1 prio=5 os_prio=0 tid=0x0000000001c3f000 nid=0x2b64 waiting on condition [0x0000000001ebf000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
    at java.lang.Thread.sleep(Native Method)
    at pl.tkowalcz.examples.basic.MonitorVsParkTest.main(MonitorVsParkTest.java:26)

   Locked ownable synchronizers:
    - <0x00000000d5b7b6b0> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
    
"Thread-0" #10 prio=5 os_prio=0 tid=0x0000000059027000 nid=0x2d30 waiting on condition [0x0000000059cdf000]
   java.lang.Thread.State: TIMED_WAITING (parking)
    at sun.misc.Unsafe.park(Native Method)
    - parking to wait for  <0x00000000d5b7b6b0> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
    at java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:215)
    at java.util.concurrent.locks.AbstractQueuedSynchronizer.doAcquireNanos(AbstractQueuedSynchronizer.java:934)
    at java.util.concurrent.locks.AbstractQueuedSynchronizer.tryAcquireNanos(AbstractQueuedSynchronizer.java:1247)
    at java.util.concurrent.locks.ReentrantLock.tryLock(ReentrantLock.java:442)
    at pl.tkowalcz.examples.basic.MonitorVsParkTest.lambda$0(MonitorVsParkTest.java:18)
    at pl.tkowalcz.examples.basic.MonitorVsParkTest$$Lambda$1/471910020.run(Unknown Source)
    at java.lang.Thread.run(Thread.java:745)

   Locked ownable synchronizers:
    - None    

*/