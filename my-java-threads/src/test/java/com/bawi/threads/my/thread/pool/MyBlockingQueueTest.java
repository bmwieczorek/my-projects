package com.bawi.threads.my.thread.pool;

import org.junit.Test;

import com.bawi.threads.my.thread.pool.MyBlockingQueue;

public class MyBlockingQueueTest {

	@Test
	public void test() throws InterruptedException {
		MyBlockingQueue<Object> myNewQueue = new MyBlockingQueue<>(1);
		new Thread(() -> { 
			try {
				myNewQueue.put(new Object());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		myNewQueue.take();
	}

}
