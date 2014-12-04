package com.bawi.java8.parallel;


public class Period {
    private int datesCount;
    public void add(int datesCount){
        this.datesCount = this.datesCount + datesCount;
    }
    public int getDatesCount() {
        return datesCount;
    }
    @Override
    public String toString() {
        return "Period [datesCount=" + datesCount + "]";
    }

}
