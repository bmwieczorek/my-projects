package com.bawi.java8.parallel;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ResultCollector implements Collector<CSVLine, MutableAccumulation, Result> {

    private final PeriodFactory periodFactory;
    private final ResultFactory resultFactory;

    public ResultCollector(PeriodFactory periodFactory, ResultFactory resultFactory) {
        this.periodFactory = periodFactory;
        this.resultFactory = resultFactory;
    }

    @Override
    public Supplier<MutableAccumulation> supplier() {
        return () -> new MutableAccumulation(periodFactory, resultFactory);
    }

    @Override
    public BiConsumer<MutableAccumulation, CSVLine> accumulator() {
        return (mutableAccumulationResult, data) -> mutableAccumulationResult.add(data);
    }

    @Override
    public BinaryOperator<MutableAccumulation> combiner() {
        return (m1, m2) -> m1.accumulate(m2);
    }

    @Override
    public Function<MutableAccumulation, Result> finisher() {
        return (mutableAccumulationResult) -> mutableAccumulationResult.createFinalResult();
    }

    @Override
    public Set<java.util.stream.Collector.Characteristics> characteristics() {
        return EnumSet.of(Characteristics.UNORDERED);
    }
}