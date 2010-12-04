package com.bawi.servicemix.logging;

import org.apache.log4j.Logger;

public class MyLogger {

    private final static Logger logger = Logger.getLogger(MyLogger.class);
    private final static String PREFIX = "Logging component: ";
    private Class<?> clazz;

    public MyLogger() {
        logger.debug(PREFIX + "started !!! ");
    }

    public MyLogger(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void error(String message) {
        getLog4jLogger().error(PREFIX + message);
    }

    private Logger getLog4jLogger() {
        return Logger.getLogger(clazz);
    }

}
