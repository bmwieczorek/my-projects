package com.bawi.java8.sequential.vs.parallel;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.bawi.java8.stats.Stats;

public class SleepStatsCollector implements Collector<Double, SleepMutableStats, Stats> {

    @Override
    public Supplier<SleepMutableStats> supplier() {
        return SleepMutableStats::new;
    }

    @Override
    public BiConsumer<SleepMutableStats, Double> accumulator() {
        return (ms, i) -> ms.add(i);
    }

    @Override
    public BinaryOperator<SleepMutableStats> combiner() {
        return (ms1, ms2) -> { ms1.combine(ms2); return ms1; };
    }

    @Override
    public Function<SleepMutableStats, Stats> finisher() {
        return (ms) -> ms.createStats();
    }

    @Override
    public Set<java.util.stream.Collector.Characteristics> characteristics() {
        return EnumSet.of(Characteristics.UNORDERED);
    }
}