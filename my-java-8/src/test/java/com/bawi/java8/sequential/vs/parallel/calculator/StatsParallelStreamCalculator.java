package com.bawi.java8.sequential.vs.parallel.calculator;

import java.util.List;

import com.bawi.java8.stats.Stats;
import com.bawi.java8.stats.StatsCollector;

public class StatsParallelStreamCalculator extends Calculator {

    @Override
    public void calculate(List<Double> doubles) {
        Stats stats = doubles.
            parallelStream().
            collect(new StatsCollector());
        System.out.println(stats);
    }

}