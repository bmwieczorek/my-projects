package com.bawi;

import org.apache.log4j.Logger;

public class LogMe {
    private static final Logger logger = Logger.getLogger(LogMe.class);

    public static void main(String[] args) {
        logger.info("Info");
    }
}
