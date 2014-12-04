package com.bawi.java8.sequential.vs.parallel.calculator;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class Calculator {
    public double calculateAndGetDuration(List<Double> doubles) throws InterruptedException {
        System.out.println("*********" + this.getClass().getSimpleName() + "*********");
        System.gc();
        TimeUnit.SECONDS.sleep(5);
        long start = System.currentTimeMillis();

        calculate(doubles);

        long stop = System.currentTimeMillis();
        long duration = stop - start;
        System.out.println("Took ms: " + duration);
        System.out.println("*********" + this.getClass().getSimpleName() + "*********");
        return duration;
    }
    public abstract void calculate(List<Double> list);
}