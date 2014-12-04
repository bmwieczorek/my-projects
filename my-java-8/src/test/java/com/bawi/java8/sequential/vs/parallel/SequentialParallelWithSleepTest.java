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

public class SequentialParallelWithSleepTest {

    @Test
    public void testSequentailAndParallel() throws Exception {
        //set Sleeper to 1ms

        Random r = new Random();
        DoubleStream doubleStream = r.doubles(0d, 9999999d).
        limit(1000);

        int testRepetitionCount = 10;
        List<Double> sequentialCalculatorDuration = new ArrayList<>();
        List<Double> statsParallelStreamCalculatorDuration = new ArrayList<>();

        SequentialCalculator sequentialCalculator = new SequentialCalculator();
        StatsParallelStreamCalculator statsParallelStreamCalculator = new StatsParallelStreamCalculator();

        List<Double> doubles = doubleStream.boxed().collect(Collectors.toList());
        for (int i = 0; i < testRepetitionCount; i++) {
            sequentialCalculatorDuration.add(sequentialCalculator.calculateAndGetDuration(doubles));
            statsParallelStreamCalculatorDuration.add(statsParallelStreamCalculator.calculateAndGetDuration(doubles));
        }

        StatsCollector statsCollector = new StatsCollector();
        System.out.println("sequentialCalculatorDuration: " + sequentialCalculatorDuration.stream().collect(statsCollector));
        System.out.println("statsParallelStreamCalculatorDuration: " + statsParallelStreamCalculatorDuration.stream().collect(statsCollector));

        
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
