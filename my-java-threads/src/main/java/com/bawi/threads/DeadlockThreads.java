package com.bawi.threads;

public class DeadlockThreads {

    static class Thread0 extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " started");
            synchR();
            System.out.println(Thread.currentThread().getName() + " stopped");
        }
    }; 
     
    public static void main(String[] args) { 
        System.out.println(Thread.currentThread().getName() + " started");
        new Thread0().start();
        synchM();
        System.out.println(Thread.currentThread().getName() + " stopped");
    }
     
    static synchronized void synchM() { // is synchronized implicitly DeadlockThreads.class
        System.out.println(Thread.currentThread().getName() + " entered sync m");
        sleep(10);
        System.out.println(Thread.currentThread().getName() + " just before calling synchR");
        synchR();
        System.out.println(Thread.currentThread().getName() + " left sync m");
    }

    static void synchR() {
        synchronized (Thread0.class) {
            System.out.println(Thread.currentThread().getName() + " entered sync runnable");
            DeadlockThreads.sleep(50);
            System.out.println(Thread.currentThread().getName() + " just before calling synchM");
            DeadlockThreads.synchM();
            System.out.println(Thread.currentThread().getName() + " left sync runnable");
        }
    }
    
    static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


// Two threads (main, and Thread0) are started and they immediately enter synchronized blocks 
//  having different locks (on DeadlockThreads.class and Thread.class).
// After some sleep time one of the threads while still being in the synchronized block 
// (and keeping its lock) tries to call another synchronized method associated to different 
// lock that is in initially taken by another thread. That another thread after finishing 
// sleeping in the synchronized method calls the other synchronized method (associated to the
// lock that is owned by another thread). As a result both of the threads wait on each other. 