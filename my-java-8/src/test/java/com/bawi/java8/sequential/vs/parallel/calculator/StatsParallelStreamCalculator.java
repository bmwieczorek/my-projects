package com.bawi.java8.sequential.vs.parallel.calculator;

import java.util.List;

import com.bawi.java8.sequential.vs.parallel.SleepStatsCollector;
import com.bawi.java8.stats.Stats;

public class StatsParallelStreamCalculator extends Calculator {

    @Override
    public void calculate(List<Double> doubles) {
        Stats stats = doubles.
            parallelStream().
            collect(new SleepStatsCollector());
        System.out.println(stats);
    }

}