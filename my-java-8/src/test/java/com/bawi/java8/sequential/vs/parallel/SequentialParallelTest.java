package com.bawi.java8.sequential.vs.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.junit.Test;

import com.bawi.java8.sequential.vs.parallel.calculator.MinMaxAvgParallelStreamCalculator;
import com.bawi.java8.sequential.vs.parallel.calculator.MinMaxAvgSequentialStreamCalculator;
import com.bawi.java8.sequential.vs.parallel.calculator.SequentialCalculator;
import com.bawi.java8.sequential.vs.parallel.calculator.StatsParallelStreamCalculator;
import com.bawi.java8.sequential.vs.parallel.calculator.StatsSequentialStreamCalculator;
import com.bawi.java8.stats.StatsCollector;

public class SequentialParallelTest {

    @Test
    public void testSequentailAndParallel() throws Exception {
        Sleeper.setSleepMillis(0); //set Sleeper to 0ms

        Random r = new Random();
        DoubleStream doubleStream = r.doubles(0d, 9999999d).
        limit(15000000);
        List<Double> doubles = doubleStream.boxed().collect(Collectors.toList());

        List<Double> sequentialCalculatorDuration = new ArrayList<>();
        List<Double> minMaxAvgSequentialStreamCalculatorDuration = new ArrayList<>();
        List<Double> minMaxAvgParallelStreamCalculatorDuration = new ArrayList<>();
        List<Double> statsSequentialStreamCalculatorDuration = new ArrayList<>();
        List<Double> statsParallelStreamCalculatorDuration = new ArrayList<>();

        SequentialCalculator sequentialCalculator = new SequentialCalculator();
        MinMaxAvgSequentialStreamCalculator minMaxAvgSequentialStreamCalculator = new MinMaxAvgSequentialStreamCalculator();
        MinMaxAvgParallelStreamCalculator minMaxAvgParallelStreamCalculator = new MinMaxAvgParallelStreamCalculator();
        StatsSequentialStreamCalculator statsSequentialStreamCalculator = new StatsSequentialStreamCalculator();
        StatsParallelStreamCalculator statsParallelStreamCalculator = new StatsParallelStreamCalculator();

        int testRepetitionCount = 10;
        for (int i = 0; i < testRepetitionCount; i++) {
            sequentialCalculatorDuration.add(sequentialCalculator.calculateAndGetDuration(doubles));
            minMaxAvgSequentialStreamCalculatorDuration.add(minMaxAvgSequentialStreamCalculator.calculateAndGetDuration(doubles));
            minMaxAvgParallelStreamCalculatorDuration.add(minMaxAvgParallelStreamCalculator.calculateAndGetDuration(doubles));
            statsSequentialStreamCalculatorDuration.add(statsSequentialStreamCalculator.calculateAndGetDuration(doubles));
            statsParallelStreamCalculatorDuration.add(statsParallelStreamCalculator.calculateAndGetDuration(doubles));
        }

        StatsCollector statsCollector = new StatsCollector();
        System.out.println("sequentialCalculatorDuration: " + sequentialCalculatorDuration.stream().collect(statsCollector));
        System.out.println("minMaxAvgSequentialStreamCalculatorDuration: " + minMaxAvgSequentialStreamCalculatorDuration.stream().collect(statsCollector));
        System.out.println("minMaxAvgParallelStreamCalculatorDuration: " + minMaxAvgParallelStreamCalculatorDuration.stream().collect(statsCollector));
        System.out.println("statsSequentialStreamCalculatorDuration: " + statsSequentialStreamCalculatorDuration.stream().collect(statsCollector));
        System.out.println("statsParallelStreamCalculatorDuration: " + statsParallelStreamCalculatorDuration.stream().collect(statsCollector));

// 1st run
//        sequentialCalculatorDuration: Stats [min=40.0, max=70.0, avg=44.6]
//        minMaxAvgSequentialStreamCalculatorDuration: Stats [min=360.0, max=515.0, avg=474.0]
//        minMaxAvgParallelStreamCalculatorDuration: Stats [min=233.0, max=360.0, avg=258.6]
//        statsSequentialStreamCalculatorDuration: Stats [min=115.0, max=150.0, avg=120.5]
//        statsParallelStreamCalculatorDuration: Stats [min=51.0, max=65.0, avg=60.9]

// 2nd run
//        sequentialCalculatorDuration: Stats [min=32.0, max=78.0, avg=48.4]
//        minMaxAvgSequentialStreamCalculatorDuration: Stats [min=499.0, max=687.0, avg=557.1]
//        minMaxAvgParallelStreamCalculatorDuration: Stats [min=280.0, max=390.0, avg=312.0]
//        statsSequentialStreamCalculatorDuration: Stats [min=124.0, max=156.0, avg=132.6]
//        statsParallelStreamCalculatorDuration: Stats [min=62.0, max=78.0, avg=65.6]

// 3rd run
//        sequentialCalculatorDuration: Stats [min=46.0, max=94.0, avg=56.2]
//        minMaxAvgSequentialStreamCalculatorDuration: Stats [min=390.0, max=624.0, avg=447.6]
//        minMaxAvgParallelStreamCalculatorDuration: Stats [min=234.0, max=358.0, avg=254.2]
//        statsSequentialStreamCalculatorDuration: Stats [min=109.0, max=125.0, avg=113.8]
//        statsParallelStreamCalculatorDuration: Stats [min=47.0, max=78.0, avg=57.9]

    }
}
