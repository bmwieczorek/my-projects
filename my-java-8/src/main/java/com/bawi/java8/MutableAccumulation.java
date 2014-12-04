package com.bawi.java8;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MutableAccumulation {
    private static Logger LOGGER = LoggerFactory.getLogger(CSVLine.class);
    private Set<Long> emdNumbers = new HashSet<>();
    private int count;
    public void add(CSVLine cSVLine){
        emdNumbers.add(cSVLine.getEmdNumber());
        count += 1;
    }
    public EmdStatsResult getAccumulatedResult() {
        EmdStatsResult emdStatsResult = new EmdStatsResult(count, emdNumbers);
        LOGGER.debug("Created final accumulated result {}", emdStatsResult);
        return emdStatsResult;
    }
    public MutableAccumulation accumulate(MutableAccumulation mutableAccumulation) {
        LOGGER.debug("Accumulating intermediate {} with {}", this, mutableAccumulation);
        count += mutableAccumulation.getCount();
        emdNumbers.addAll(mutableAccumulation.getUniqueEmdNumbers());
        LOGGER.debug("Accumulation intermediate result {}", this);
        return this;
    }
    public Set<Long> getUniqueEmdNumbers() {
        return emdNumbers;
    }
    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "MutableAccumulation [emdNumbers=" + emdNumbers + ", count=" + count + "]";
    }
}