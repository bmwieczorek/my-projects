package com.bawi.threads.my.thread.pool;

import java.util.List;

public class MyThreadPool {

    private final MyBlockingQueue<Runnable> blockingQueue;
    private final List<MyWorkerThread> workerThreads;
    private volatile boolean isShutdown = false;

    public MyThreadPool(List<MyWorkerThread> workerThreads, MyBlockingQueue<Runnable> blockingQueue) {
        this.workerThreads = workerThreads;
        this.blockingQueue = blockingQueue;
    }

    public void start() {
        workerThreads.stream().forEach(MyWorkerThread::start);
    }

    public void submit(Runnable task) {
        if (!isShutdown) {
            try {
                blockingQueue.put(task);
            } catch (InterruptedException e) {
                System.out.println("Interrupted putting new task on a processing blockingQueue");
            }
        }
    }

    public void shutdownNow() {
        isShutdown = true;
        workerThreads.stream().forEach(MyWorkerThread::shutdown);
    }

    public static void main(String[] args) throws InterruptedException {
        MyThreadPool myThreadPool = new MyThreadPoolBuilder()
                .withWorkerThreadCount(2)
                .withBlockingQueueSize(4)
                .buildAndStart();

        myThreadPool.submit(createTask(1));
        myThreadPool.submit(createTask(2));
        myThreadPool.submit(createTask(3));
        myThreadPool.submit(createTask(4));
        myThreadPool.submit(createTask(5));
        myThreadPool.submit(createTask(6));

        Thread.sleep(10); // sleep until all tasks will be picked up for processing
        myThreadPool.shutdownNow();
    }

    private static Runnable createTask(int taskNumber) {
        return () -> {
            System.out.println("[" + Thread.currentThread().getName() + "] Hello from task #" + taskNumber);
        };
    }

}
