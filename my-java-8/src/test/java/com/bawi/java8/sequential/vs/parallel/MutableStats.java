package com.bawi.java8.sequential.vs.parallel;

import com.bawi.java8.sequential.vs.parallel.Sleeper;



public class MutableStats {
    public double min = Double.MAX_VALUE, max = Double.MIN_VALUE, sum;
    public int count;
    public void add(double d) {
        min = min <= d ? min : d;
        max = max >= d ? max : d;
        sum += d;
        count += 1;
        Sleeper.sleep();
    }
    public void combine(MutableStats ms) {
        min = min <= ms.min ? min : ms.min; 
        max = max >= ms.max ? max : ms.max;
        sum += ms.sum;
        count += ms.count;
        Sleeper.sleep();
    }
    public Stats createStats() {
        return new Stats(min, max, sum/count);
    }
}