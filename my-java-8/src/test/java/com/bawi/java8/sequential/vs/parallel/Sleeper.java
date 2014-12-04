package com.bawi.java8.sequential.vs.parallel;

import java.util.concurrent.TimeUnit;

public class Sleeper {

    public static void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
