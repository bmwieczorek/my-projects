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
    public void testSequentailAndParallelWithSleep() throws Exception {
        Sleeper.setSleepMillis(1);//set Sleeper to 1ms

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

//        sequentialCalculatorDuration: Stats [min=1029.0, max=1137.0, avg=1052.0]
//        statsParallelStreamCalculatorDuration: Stats [min=284.0, max=382.0, avg=295.1]

    }
}
