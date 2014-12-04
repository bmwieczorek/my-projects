package com.bawi.java8.sequential.vs.parallel.calculator;

import java.util.List;

public class MinMaxAvgParallelStreamCalculator extends Calculator {

    @Override
    public void calculate(List<Double> doubles) {
        double average = doubles.
            parallelStream().
            mapToDouble(t -> (double) t).
            average().
            getAsDouble();
        double min = doubles.
            parallelStream().
            mapToDouble(t -> (double) t).
            min().
            getAsDouble();
        double max = doubles.
            parallelStream().
            mapToDouble(t -> (double) t).
            max().
            getAsDouble();
        System.out.println(min);
        System.out.println(max);
        System.out.println(average);
    }

}