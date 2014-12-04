package com.bawi.java8.stats;

public class MutableStats {
    public double min = Double.MAX_VALUE, max = Double.MIN_VALUE, sum;
    public int count;

    public void add(double d) {
        min = min <= d ? min : d;
        max = max >= d ? max : d;
        sum += d;
        count += 1;
    }

    public void combine(MutableStats ms) {
        min = min <= ms.min ? min : ms.min;
        max = max >= ms.max ? max : ms.max;
        sum += ms.sum;
        count += ms.count;
    }

    public Stats createStats() {
        return new Stats(min, max, sum / count);
    }
}