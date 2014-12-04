package com.bawi.java8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSVLine {
    private static Logger LOGGER = LoggerFactory.getLogger(CSVLine.class);
    private final long emdNumber;
    public CSVLine(long emdNumber) {
        this.emdNumber = emdNumber;
        LOGGER.debug("Created {}", this);
    }
    public long getEmdNumber() {
        return emdNumber;
    }
    @Override
    public String toString() {
        return "CSVLine [emdNumber=" + emdNumber + "]";
    }
}