package com.bawi.java8.stats;

public class Stats {
    private double min, max, avg;
    public Stats(double min, double max, double avg) {
        this.min = min;
        this.max = max;
        this.avg = avg;
    }
    public double getMin() {
        return min;
    }
    public double getMax() {
        return max;
    }
    public double getAvg() {
        return avg;
    }
    @Override
    public String toString() {
        return "Stats [min=" + min + ", max=" + max + ", avg=" + avg + "]";
    }
}