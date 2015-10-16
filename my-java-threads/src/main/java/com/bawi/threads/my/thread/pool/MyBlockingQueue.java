package com.bawi.threads.my.thread.pool;

import java.util.LinkedList;

public class MyBlockingQueue<T> {
	
	private LinkedList<T> linkedList = new LinkedList<>();
	private final int size;
	
	public MyBlockingQueue(int size) {
		this.size = size;
	}
	
	public synchronized T take() throws InterruptedException {
		while (linkedList.size() == 0) {
			wait();
		};
		notify();
		return linkedList.removeLast();
	}
	
	public synchronized void put(T element) throws InterruptedException {
		while (linkedList.size() == size) {
			wait();
		}
		linkedList.addFirst(element);
		notify();
	}
}
