package com.bawi.java8.parallel;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSVLine {

    private static Logger LOGGER = LoggerFactory.getLogger(CSVLine.class);

    private final long emdNumber;
    private final Date date;

    public CSVLine(long emdNumber, Date date) {
        this.emdNumber = emdNumber;
        this.date = date;
        LOGGER.debug("Created {}", this);
    }

    public long getEmdNumber() {
        return emdNumber;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "CSVLine [emdNumber=" + emdNumber + ", date=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date) + "]";
    }
}