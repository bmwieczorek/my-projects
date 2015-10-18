package com.bawi.threads;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyRaceConditionAndSolution {

    static class NotSynchronizedNotAtomicCounter {
        private long value = 0;
        public void add(long v) {
            value = value + v;
        }
        public long getValue() {
            return value;
        }
    }

    static class SynchronizedCounter {
        private long value = 0;

        // optionally instead of synchronized(this) {    } 
        // public synchronized void add(long v) {
        public void add(long v) {
            synchronized (this) {
                value = value + v;
            }
        }
        public long getValue() {
            return value;
        }

    }
    
    static class AtomicCounter {
        private AtomicLong value = new AtomicLong();
        public void add(long v) {
            value.getAndAdd(v);
        }
        public long getValue() {
            return value.get();
        }
    }

    static class ReentrantLockCounter {
        private Lock lock = new ReentrantLock();
        private long value = 0;

        public void add(long v) {
            lock.lock();
            value = value + v;
            lock.unlock();
        }
        public long getValue() {
            return value;
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            NotSynchronizedNotAtomicCounter counter = new NotSynchronizedNotAtomicCounter();
            // SynchronizedCounter counter = new SynchronizedCounter();
            // ReentrantLockCounter counter = new ReentrantLockCounter();
            // AtomicCounter counter = new AtomicCounter();

            Thread t1 = new Thread(() -> counter.add(1));
            Thread t2 = new Thread(() -> counter.add(2));
            t1.start();
            t2.start();

            t1.join();
            t2.join();

            long value = counter.getValue();
            // System.out.println("value=" + value);

            if (value != 3L) {
                throw new RuntimeException("value=" + value);
            }
        }
    }
}

/*
 * Output for NotSynchronizedNotAtomicCounter without any  
 * value=3 ... value=3 value=1 Exception in thread "main" java.lang.RuntimeException: value=1 at
 * com.bawi.threads.my.thread.pool.MyRaceConditionAndSolution.main(MyRaceConditionAndSolution.java:33)
 * 
 */