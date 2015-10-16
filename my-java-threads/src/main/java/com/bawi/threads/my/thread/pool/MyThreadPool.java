package com.bawi.threads.my.thread.pool;

import java.util.ArrayList;
import java.util.List;

public class MyThreadPool {
	
	private final MyBlockingQueue<Runnable> queue;
	private final List<MyWorkerThread> workerThreads;
	private int workerThreadsCount;
	private volatile boolean isShutdown = false;
	
	public MyThreadPool(int workerThreadsCount, int blockingQueueSize) {
		this.workerThreadsCount = workerThreadsCount;
		this.queue = new MyBlockingQueue<>(blockingQueueSize);
		this.workerThreads = new ArrayList<>(workerThreadsCount);
	}
	
	public void init() {
		for (int i = 0; i < workerThreadsCount; i++) {
			MyWorkerThread workerThread = new MyWorkerThread(queue);
			workerThreads.add(workerThread);
		}
		start();
	}
	
	public void start() {
		workerThreads.stream().forEach(MyWorkerThread::start);
	}
	
	public void submit(Runnable task) {
		if (!isShutdown) {
			try {
				queue.put(task);
			} catch (InterruptedException e) {
				System.out.println("Interrupted putting new task on a processing queue");
			}
		}
	}

	public void shutdownNow() {
		isShutdown = true;
		workerThreads.stream().forEach(MyWorkerThread::shutdown);
	}

	
	public static void main(String[] args) throws InterruptedException {
		MyThreadPool myThreadPool = new MyThreadPool(2, 4);
		myThreadPool.init();

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
