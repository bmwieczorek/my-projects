package com.bawi.java8.sequential.vs.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.junit.Test;

import com.bawi.java8.stats.Stats;
import com.bawi.java8.stats.StatsCollector;

public class SequentialParallelTest {

    @Test
    public void testSequentailAndParallel() throws Exception {
        //set Sleeper to 0ms

        Random r = new Random();
        DoubleStream doubleStream = r.doubles(0d, 9999999d).
        limit(15000000);

        int testRepetitionCount = 10;
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

        List<Double> doubles = doubleStream.boxed().collect(Collectors.toList());
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

    }

    abstract class Calculator {
        double calculateAndGetDuration(List<Double> doubles) throws InterruptedException {
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
        abstract void calculate(List<Double> list);
    }

    class SequentialCalculator extends Calculator {

        @Override
        void calculate(List<Double> list) {
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

    class MinMaxAvgSequentialStreamCalculator extends Calculator {

        @Override
        void calculate(List<Double> doubles) {
            double average = doubles.
                stream().
                mapToDouble(t -> (double) t).
                average().
                getAsDouble();
            double min = doubles.
                stream().
                mapToDouble(t -> (double) t).
                min().
                getAsDouble();
            double max = doubles.
                stream().
                mapToDouble(t -> (double) t).
                max().
                getAsDouble();
            System.out.println(min);
            System.out.println(max);
            System.out.println(average);
        }

    }

    class MinMaxAvgParallelStreamCalculator extends Calculator {

        @Override
        void calculate(List<Double> doubles) {
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

    class StatsSequentialStreamCalculator extends Calculator {

        @Override
        void calculate(List<Double> doubles) {
            Stats stats = doubles.
                stream().
                collect(new StatsCollector());
            System.out.println(stats);
        }

    }

    class StatsParallelStreamCalculator extends Calculator {

        @Override
        void calculate(List<Double> doubles) {
            Stats stats = doubles.
                parallelStream().
                collect(new StatsCollector());
            System.out.println(stats);
        }

    }
}
