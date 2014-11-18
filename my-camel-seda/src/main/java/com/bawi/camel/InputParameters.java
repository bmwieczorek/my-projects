package com.bawi.camel;

public class InputParameters {

    private final int maximumRequestCount;

    public InputParameters(int maximumRequestCount) {
        this.maximumRequestCount = maximumRequestCount;
    }

    public int getMaximumRequestCount() {
        return maximumRequestCount;
    }

    @Override
    public String toString() {
        return "InputParameters [maximumRequestCount=" + maximumRequestCount + "]";
    }

}
