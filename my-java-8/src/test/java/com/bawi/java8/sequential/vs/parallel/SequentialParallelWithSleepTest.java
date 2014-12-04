package com.bawi.java8.sequential.vs.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.junit.Test;

import com.bawi.java8.sequential.vs.parallel.calculator.SequentialCalculator;
import com.bawi.java8.sequential.vs.parallel.calculator.StatsParallelStreamCalculator;
import com.bawi.java8.stats.StatsCollector;

public class SequentialParallelWithSleepTest {

    @Test
    public void testSequentailAndParallel() throws Exception {
        //set Sleeper to 1ms

        Random r = new Random();
        DoubleStream doubleStream = r.doubles(0d, 9999999d).
        limit(1000);
        List<Double> doubles = doubleStream.boxed().collect(Collectors.toList());

        List<Double> sequentialCalculatorDuration = new ArrayList<>();
        List<Double> statsParallelStreamCalculatorDuration = new ArrayList<>();

        SequentialCalculator sequentialCalculator = new SequentialCalculator();
        StatsParallelStreamCalculator statsParallelStreamCalculator = new StatsParallelStreamCalculator();

        int testRepetitionCount = 10;
        for (int i = 0; i < testRepetitionCount; i++) {
            sequentialCalculatorDuration.add(sequentialCalculator.calculateAndGetDuration(doubles));
            statsParallelStreamCalculatorDuration.add(statsParallelStreamCalculator.calculateAndGetDuration(doubles));
        }

        StatsCollector statsCollector = new StatsCollector();
        System.out.println("sequentialCalculatorDuration: " + sequentialCalculatorDuration.stream().collect(statsCollector));
        System.out.println("statsParallelStreamCalculatorDuration: " + statsParallelStreamCalculatorDuration.stream().collect(statsCollector));

//        sequentialCalculatorDuration: Stats [min=1030.0, max=1168.0, avg=1070.0]
//        statsParallelStreamCalculatorDuration: Stats [min=0.0, max=109.0, avg=10.9]

    }
}
