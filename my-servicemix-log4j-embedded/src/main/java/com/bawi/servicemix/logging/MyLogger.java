package com.bawi.servicemix.logging;

import org.apache.log4j.Logger;

import com.bawi.servicemix.logging.context.LoggableContext;
import com.bawi.servicemix.logging.context.LoggableContextProvider;

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

    public void saveMessage(String message) {
        LoggableContext context = LoggableContextProvider.getContext();
        context.addLoggableEvent(message);
    }

    public void debug(String message) {
        saveMessage(message);
        getLog4jLogger().debug(PREFIX + message);
    }

    public void error(String message) {
        saveMessage(message);
        LoggableContext context = LoggableContextProvider.getContext();
        getLog4jLogger().error(PREFIX + context.getAllEventsAsLogString());
    }

    private Logger getLog4jLogger() {
        return Logger.getLogger(clazz);
    }

}
