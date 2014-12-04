package com.bawi.java8.sequential.vs.parallel;

import java.util.concurrent.TimeUnit;

public class Sleeper {
    
    private static long sleepMillis = 0L;

    public static void setSleepMillis(long sleepMillis) {
        Sleeper.sleepMillis = sleepMillis;
    }

    public static void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(sleepMillis);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
