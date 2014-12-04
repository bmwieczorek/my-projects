package com.bawi.java8.stats;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StatsCollector implements Collector<Double, MutableStats, Stats> {

    @Override
    public Supplier<MutableStats> supplier() {
        return MutableStats::new;
    }

    @Override
    public BiConsumer<MutableStats, Double> accumulator() {
        return (ms, i) -> ms.add(i);
    }

    @Override
    public BinaryOperator<MutableStats> combiner() {
        return (ms1, ms2) -> { ms1.combine(ms2); return ms1; };
    }

    @Override
    public Function<MutableStats, Stats> finisher() {
        return (ms) -> ms.createStats();
    }

    @Override
    public Set<java.util.stream.Collector.Characteristics> characteristics() {
        return EnumSet.of(Characteristics.UNORDERED);
    }
}