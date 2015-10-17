package com.bawi.threads;

public class ThreadRunVsStart {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName() + " start")).start();
        new Thread(() -> System.out.println(Thread.currentThread().getName() + " run")).run();
    }
}
// Thread-0 start
// run start
