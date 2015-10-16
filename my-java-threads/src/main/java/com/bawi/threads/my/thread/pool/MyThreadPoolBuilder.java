package com.bawi.threads.my.thread.pool;

import java.util.ArrayList;
import java.util.List;

public class MyThreadPoolBuilder {
	
	private int workerThreadsCount;
	private int blockingQueueSize;
	
	public MyThreadPoolBuilder withWorkerThreadCount(int count){
		workerThreadsCount = count;
		return this;
	}
	
	public MyThreadPoolBuilder withBlockingQueueSize(int size) {
		blockingQueueSize = size;
		return this;
	}
	
	public MyThreadPool build() {
		validate();
		MyBlockingQueue<Runnable> blockingQueue = new MyBlockingQueue<>(blockingQueueSize);
		List<MyWorkerThread> workerThreads = new ArrayList<>(workerThreadsCount);
		for (int i = 0; i < workerThreadsCount; i++) {
			workerThreads.add(new MyWorkerThread(blockingQueue));
		}
		return new MyThreadPool(workerThreads, blockingQueue);
	}

	private void validate() {
		if (blockingQueueSize <= 0) {
			throw new IllegalArgumentException("Blocking queue size must me positive");
		}
		if (workerThreadsCount <= 0) {
			throw new IllegalArgumentException("Worker thread count must me positive");
		}
	}
	
	public MyThreadPool buildAndStart() {
		MyThreadPool myThreadPool = build();
		myThreadPool.start();
		return myThreadPool;
	}
}