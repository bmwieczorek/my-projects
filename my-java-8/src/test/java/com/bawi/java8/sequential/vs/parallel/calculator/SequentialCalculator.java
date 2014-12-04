package com.bawi.java8.sequential.vs.parallel.calculator;

import java.util.List;

import com.bawi.java8.sequential.vs.parallel.Sleeper;

public class SequentialCalculator extends Calculator {

    @Override
    public void calculate(List<Double> list) {
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE, sum = 0;
        int count = 0;
        for (Double d : list) {
            min = min <= d ? min : d;
            max = max >= d ? max : d;
            sum += d;
            count++;
            Sleeper.sleep();
        }
        System.out.println(min);
        System.out.println(max);
        System.out.println((double)(sum/count));
    }

}