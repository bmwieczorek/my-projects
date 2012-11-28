package com.bawi;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class TestLog4j {
    private LogMe logMe = new LogMe();

    @Ignore
    @Test
    public void shouldLog() throws IOException {
        // given
        Logger logger = Logger.getLogger(LogMe.class);
        assertEquals(Level.DEBUG, logger.getLevel());
        String logFilePath = "target/file.log";
        assertHasExactlyOneRollingFileAppenderLoggingToFile(logger, logFilePath);

        // given
        Logger rootLogger = LogManager.getRootLogger();
        assertEquals(Level.ERROR, rootLogger.getLevel());
        String rootLogFilePath = "target/root-file.log";
        assertHasExactlyOneRollingFileAppenderLoggingToFile(rootLogger, rootLogFilePath);

        // when
        logMe.doIt();

        // then
        List<?> logFileLines = FileUtils.readLines(new File(logFilePath));
        assertEquals(1, logFileLines.size());

        // then
        List<?> rootLogFileLines = FileUtils.readLines(new File(rootLogFilePath));
        assertEquals(1, rootLogFileLines.size());

    }

    private void assertHasExactlyOneRollingFileAppenderLoggingToFile(Logger logger, String logFilePath) {
        Enumeration<?> allAppenders = logger.getAllAppenders();

        int numberOfRollingFileAppenders = 0;
        int numberOfOtherAppenders = 0;
        while (allAppenders.hasMoreElements()) {
            Object object = allAppenders.nextElement();
            if (object instanceof RollingFileAppender) {
                RollingFileAppender rollingFileAppender = (RollingFileAppender) object;
                Assert.assertEquals(logFilePath, rollingFileAppender.getFile());
                numberOfRollingFileAppenders++;
            } else {
                numberOfOtherAppenders++;
            }
        }
        Assert.assertEquals(1, numberOfRollingFileAppenders);
        Assert.assertEquals(0, numberOfOtherAppenders);
    }
}
