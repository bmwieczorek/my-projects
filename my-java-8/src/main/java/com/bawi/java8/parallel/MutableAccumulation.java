package com.bawi.java8.parallel;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MutableAccumulation {

    private static Logger LOGGER = LoggerFactory.getLogger(MutableAccumulation.class);
    private final PeriodFactory periodFactory;
    private final ResultFactory resultFactory;

    private Set<Long> emdNumbers = new HashSet<>();
    private Map<Date, Period> periods = new HashMap<>();
    private Date lastPeriodStartDate = new Date(0L);
    private int count;

    public MutableAccumulation(PeriodFactory periodFactory, ResultFactory resultFactory) {
        this.periodFactory = periodFactory;
        this.resultFactory = resultFactory;
    }

    public void add(CSVLine csvLine) {
        if (csvLine == null) { 
            return;
        }
        emdNumbers.add(csvLine.getEmdNumber());
        count += 1;
        Date date = csvLine.getDate();
        Date periodStartDate = periodFactory.getPeriodStartDate(date);
        lastPeriodStartDate = periodStartDate;
        if (periods.containsKey(periodStartDate)) {
            Period period = periods.get(periodStartDate);
            period.add(1);
        } else {
            Period period = new Period();
            period.add(1);
            periods.put(periodStartDate, period);
        }
    }

    public MutableAccumulation accumulate(MutableAccumulation mutableAccumulation) {
        LOGGER.debug("Accumulating intermediate {} with {}", this, mutableAccumulation);
        if (mutableAccumulation == null) {
            return this;
        }
        emdNumbers.addAll(mutableAccumulation.getUniqueEmdNumbers());
        count += mutableAccumulation.getCount();
        lastPeriodStartDate = lastPeriodStartDate.compareTo(mutableAccumulation.getLastPeriodStartDate()) > 0 ? lastPeriodStartDate : mutableAccumulation.getLastPeriodStartDate();
        Map<Date, Period> mutablePeriods = mutableAccumulation.getPeriods();
        for (Date mutableStartDate : mutablePeriods.keySet()) {
            if (periods.containsKey(mutableStartDate)) {
                Period period = periods.get(mutableStartDate);
                Period mutablePeriod = mutablePeriods.get(mutableStartDate);
                period.add(mutablePeriod.getDatesCount());
            } else {
                periods.put(mutableStartDate, mutablePeriods.get(mutableStartDate));
            }
        }
        LOGGER.debug("Accumulation intermediate result {}", this);
        return this;
    }

    public Result createFinalResult() {
        Result result = resultFactory.createResult(this);
        LOGGER.debug("Created final accumulated result {}", result);
        return result;
    }

    public Set<Long> getUniqueEmdNumbers() {
        return emdNumbers;
    }

    public int getCount() {
        return count;
    }

    public Map<Date, Period> getPeriods() {
        return periods;
    }
    
    public Date getLastPeriodStartDate() {
        return lastPeriodStartDate;
    }

    @Override
    public String toString() {
        return "MutableAccumulation [emdNumbers=" + emdNumbers + ", periods=" + periods + ", lastPeriodStartDate="
                + lastPeriodStartDate + ", count=" + count + "]";
    }

}