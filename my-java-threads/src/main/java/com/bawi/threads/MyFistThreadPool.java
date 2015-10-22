package com.bawi.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyFistThreadPool {
    
    static class MyBlockingQueue {
        Lock lock = new ReentrantLock();
        Condition notEmpty;
        Runnable runnable;
        
        public MyBlockingQueue() {
            lock = new ReentrantLock();
            notEmpty = lock.newCondition();
        }

        public Runnable take() throws InterruptedException {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] Take: Before lockInterruptibly");
                lock.lockInterruptibly();
                System.out.println("[" + Thread.currentThread().getName() + "] Take: After lockInterruptibly");
                while (runnable == null) {
                    System.out.println("[" + Thread.currentThread().getName() + "] Take: Before await");
                    notEmpty.await(); // releases the lock and becomes disabled for thread scheduling, start waiting for signal(all) or interrupts  
                    System.out.println("[" + Thread.currentThread().getName() + "] Take: After await");
                }
                return runnable;
            } 
            finally {
                runnable = null;
                lock.unlock();
            }
        }
        
        public void put(Runnable newRunnable) {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] Put: Before lockInterruptibly");
                lock.lockInterruptibly();
                System.out.println("[" + Thread.currentThread().getName() + "] Put: After lockInterruptibly");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                this.runnable = newRunnable;
                System.out.println("[" + Thread.currentThread().getName() + "] Put: Before signal");
                notEmpty.signal();
                System.out.println("[" + Thread.currentThread().getName() + "] Put: After signal");
            }
            finally {
                lock.unlock();
            }
        }
        
    }
    
    static class MyPool {
        
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue();
        List<Thread> workerThreads = new ArrayList<>();
        
        public static MyPool createAndStartThreadPool(int size) {
            MyPool myPool = new MyPool().withWorkerThreadCount(size);
            myPool.start();
            return myPool;
            
        }
        private MyPool withWorkerThreadCount(int size) {
            addWorkers(size);
            return this;
        }
        private void start() {
            System.out.println("[" + Thread.currentThread().getName() + "] Starting my worker threads in my pool");
            workerThreads.stream().forEach(Thread::start);
        }
        private void addWorkers(int size) {
            for (int i = 0; i < size; i++) {
                workerThreads.add(createWorkerThread());
            }
        }
        private Thread createWorkerThread() {
            return new Thread(new Runnable() {
                
                @Override
                public void run() {
                    System.out.println("[" + Thread.currentThread().getName() + "] Started my worker thread in my pool");
                    while (true) {
                        Runnable runnable;
                        try {
                            runnable = myBlockingQueue.take();
                        } catch (InterruptedException e) {
                            System.out.println("[" + Thread.currentThread().getName() + "] My worker thread interrupted, exiting due to " + e );
                            return;
                        }

                        System.out.println("[" + Thread.currentThread().getName() + "] Starting to run a task");
                        runnable.run();
                        System.out.println("[" + Thread.currentThread().getName() + "] Finished running a task");
                    }
                }
            });
        }
        public void submit(Runnable r) {
            myBlockingQueue.put(r);
        }
        public void shutdown() {
            workerThreads.stream().forEach(Thread::interrupt);
        };
    }
    
    public static void main(String[] args) {
        MyPool myPool = MyPool.createAndStartThreadPool(2);

        myPool.submit(() -> doTheWork("My Runnable 1"));
        myPool.submit(() -> doTheWork("My Runnable 2"));
        myPool.submit(() -> doTheWork("My Runnable 3"));
        
        sleepMillis(5000);

        myPool.shutdown();

    }

    static void sleepMillis(int sleepMillis) {
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void doTheWork(String name) {
        int sleepMillis = 1000;
        System.out.println("Task: " + name + ", sleeping: " + sleepMillis +  " ms ... [" + Thread.currentThread().getName() + "]"); 
        sleepMillis(sleepMillis);
    }
}
