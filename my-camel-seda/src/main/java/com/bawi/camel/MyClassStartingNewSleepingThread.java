package com.bawi.camel;

import java.util.concurrent.TimeUnit;

public class MyClassStartingNewSleepingThread {

    public void init() {
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                System.out.println("Started sleeping thread:" + Thread.currentThread().getName());
                try {
                    TimeUnit.MINUTES.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
