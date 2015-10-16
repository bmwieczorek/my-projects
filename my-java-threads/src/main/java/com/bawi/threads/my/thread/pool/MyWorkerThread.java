package com.bawi.threads.my.thread.pool;

public class MyWorkerThread extends Thread {

    private MyBlockingQueue<Runnable> queue;
    private volatile boolean isShutdown = false;

    public MyWorkerThread(MyBlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!isShutdown) {
            try {
                Runnable task = queue.take();
                task.run();
            } catch (InterruptedException e) {
                System.out.println("[" + Thread.currentThread().getName() + "] Interrupted waiting on next task");
            }
        }
    }

    public void shutdown() {
        isShutdown = true;
        interrupt();
    }
}
