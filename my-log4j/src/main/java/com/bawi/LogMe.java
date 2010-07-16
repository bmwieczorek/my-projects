package com.bawi;

import static com.bawi.Log4jConfigurationLocationFinder.getLog4jConfigurationLocation;

import org.apache.log4j.Logger;

public class LogMe {
    private static final Logger LOGGER = Logger.getLogger(LogMe.class);

    public void doIt() {
        LOGGER.info("Path=" + getLog4jConfigurationLocation());
    }
}
