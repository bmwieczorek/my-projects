package com.bawi.eventsfilter;

import java.util.Date;

public class Time {

    private long timeMillis;

    public Time() {
        timeMillis = new Date().getTime();
    }

    public Time(long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public void addTimeMillis(long timeMillisToAdd) {
        timeMillis = timeMillis + timeMillisToAdd;
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    @Override
    public String toString() {
        return "Time [timeMillis=" + timeMillis + ", date=" + new Date(timeMillis) + "]";
    }

}
