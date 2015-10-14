package com.bawi.threads;

public class WaitingAccessMutexUntilAnotherThreadFinishesAndReleasesTheLock {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + " started");
        
        new Thread(() -> {
            System.out.println(Thread.currentThread() + " started");
            doSth1();
            // doSth2();
        }).start();

        doSth1();
    }
    
    public synchronized static void doSth1() {
        try {
            System.out.println(Thread.currentThread() + " doSth1 begin");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread() + " doSth1 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void doSth2() {
        try {
            System.out.println(Thread.currentThread() + " doSth2 begin");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread() + " doSth2 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}

/*
Output:
Thread[main,5,main] started
Thread[main,5,main] doSth1 begin
Thread[Thread-0,5,main] started
Thread[main,5,main] doSth1 end
Thread[Thread-0,5,main] doSth1 begin
Thread[Thread-0,5,main] doSth1 end

Explanation:
It does not matter if the Thread-0 wants to access doSth1 or doSth2 - the result is the same as they are both synchronized 
on the same lock. So Thread-0 needs to wait for main thread to finish sleeping while keeping the lock. 
When main thread releases the lock by exiting doSth1, then Thread-0 can execute synchronized code from doSth1 or doSth2.
*/