package com.bawi.services.calculator.jmx;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(objectName = "com.bawi.services.calculator:name=Log4jLevelChanger", description = "Change log level")
public class Log4jLevelChanger {

    private static final Logger LOGGER = Logger.getLogger(Log4jLevelChanger.class);

    @ManagedOperation(description = "display log level for logger")
    public String getLogLevelForLogger(String loggerName) {
        validateNotBlank(loggerName, "logger name");
        Logger logger = Logger.getLogger(loggerName);
        String logLevel = "" + logger.getLevel();
        LOGGER.debug(logLevel);
        return logLevel;
    }

    @ManagedOperation(description = "set log level for logger")
    public String setLogLevelForLogger(String loglevel, String loggerName) {
        validateNotBlank(loglevel, "log level");
        validateNotBlank(loggerName, "logger name");

        Logger logger = Logger.getLogger(loggerName);
        String s = loglevel.toUpperCase();

        if ("ALL".equals(s)) {
            logger.setLevel(Level.ALL);
        }
        if ("DEBUG".equals(s)) {
            logger.setLevel(Level.DEBUG);
        }
        if ("INFO".equals(s)) {
            logger.setLevel(Level.INFO);
        }
        if ("WARN".equals(s)) {
            logger.setLevel(Level.WARN);
        }
        if ("ERROR".equals(s)) {
            logger.setLevel(Level.ERROR);
        }
        if ("FATAL".equals(s)) {
            logger.setLevel(Level.FATAL);
        }
        if ("OFF".equals(s)) {
            logger.setLevel(Level.OFF);
        }
        if ("TRACE".equals(s)) {
            logger.setLevel(Level.TRACE);
        }

        return "Level set to " + s + " for logger " + loggerName;
    }

    private static void validateNotBlank(String value, String name) {
        if (StringUtils.isBlank(value)) {
            String errorMessage = "Blank " + name + ": '" + value + "'";
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
