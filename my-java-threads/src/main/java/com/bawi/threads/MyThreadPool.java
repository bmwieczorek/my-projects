package com.bawi.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPool {
    
    static class MyBlockingQueue {
        Lock lock = new ReentrantLock();
        Condition notEmpty;
        Runnable r;
        
        public MyBlockingQueue() {
            lock = new ReentrantLock();
            notEmpty = lock.newCondition();
        }

        public Runnable get() throws InterruptedException {
            lock.lock(); 
            try {
                while (r == null) {
                    notEmpty.await();
                }
                return r;
            }
            finally {
                r = null;
                lock.unlock();
            }
        }
        
        public void setR(Runnable r) {
            lock.lock();
            try {
                this.r = r;
                notEmpty.signal();
            }
            finally {
                lock.unlock();
            }
        }
        
    }
    
    static class MyPool {
        
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue();
        List<Thread> workerThreads;
        volatile boolean isShutdown = false;
        
        public static MyPool createAndStartThreadsPool(int size) {
            MyPool myPool = new MyPool();
            myPool.workerThreads = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                myPool.workerThreads.add(myPool.createWorkerThread());
            }
            myPool.start();
            return myPool;
            
        }
        private void start() {
            System.out.println("Starting my pool: " + Thread.currentThread().getName());
            for (Thread workerThread : workerThreads) {
                workerThread.start();
            }
        }
        private Thread createWorkerThread() {
            return new Thread(new Runnable() {
                
                @Override
                public void run() {
                    System.out.println("Starting my thread in my pool: " + Thread.currentThread().getName());
                    while (true) {
                        Runnable runnable;
                        try {
                            if (isShutdown) {
                                System.out.println("Shutdown received: " + Thread.currentThread().getName());
                                return;
                            }
                            runnable = myBlockingQueue.get();
                            System.out.println("Starting to run a task: " + Thread.currentThread().getName());
                            runnable.run();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        public void submit(Runnable r) {
            myBlockingQueue.setR(r);
        }
        public void shutdown() {
            isShutdown = true;
            for (int i = 0; i < workerThreads.size(); i++) {
                submit(() -> { /* */} );
                sleepMillis(1);
            }
        };
    }
    
    public static void main(String[] args) {
        MyPool myPool = MyPool.createAndStartThreadsPool(2);

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
        System.out.println(name + ": " + Thread.currentThread().getName()); 
        sleepMillis(1000);
    }
}
