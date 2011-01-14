package com.bawi.servicemix.logging;

import org.apache.log4j.Logger;

public class MyLoggerImpl implements MyLogger {

    private Class<?> clazz;

    MyLoggerImpl(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void debug(String message) {
        getLog4jLogger().debug(message);
    }

    @Override
    public void info(String message) {
        getLog4jLogger().info(message);
    }

    @Override
    public void error(String message) {
        getLog4jLogger().error(message);
    }

    private Logger getLog4jLogger() {
        return Logger.getLogger(clazz);
    }

}
