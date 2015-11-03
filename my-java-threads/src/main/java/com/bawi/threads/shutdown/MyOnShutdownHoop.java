package com.bawi.threads.shutdown;

import java.util.concurrent.TimeUnit;

public class MyOnShutdownHoop {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Called shutdown hook")));
        TimeUnit.SECONDS.sleep(10000);
        System.out.println("Finishing");
    }

}
