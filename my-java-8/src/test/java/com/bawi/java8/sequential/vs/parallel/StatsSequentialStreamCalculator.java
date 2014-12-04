package com.bawi.java8.sequential.vs.parallel;

import java.util.List;

import com.bawi.java8.stats.Stats;
import com.bawi.java8.stats.StatsCollector;

public class StatsSequentialStreamCalculator extends Calculator {

    @Override
    void calculate(List<Double> doubles) {
        Stats stats = doubles.
            stream().
            collect(new StatsCollector());
        System.out.println(stats);
    }

}