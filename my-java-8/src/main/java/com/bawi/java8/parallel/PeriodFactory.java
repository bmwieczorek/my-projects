package com.bawi.java8.parallel;

import java.util.Date;

public class PeriodFactory {
    
    private final Date initialStart;
    private int periodLengthMillis = 60000;
    
    public PeriodFactory(Date initialStart) {
        this.initialStart = initialStart;
    }

    public Date getPeriodStartDate(Date date) {
        long difference = date.getTime() - initialStart.getTime();
        long delta = difference % periodLengthMillis;
        long start = date.getTime() - delta;
        return new Date(start);
    }

}
