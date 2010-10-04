package com.bawi;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.rolling.RollingFileAppender;
import org.apache.log4j.rolling.TimeBasedRollingPolicy;
import org.junit.Test;

public class TestRollingFileAppender {

    @Test
    public void should() {
        Logger logger = Logger.getLogger(TestRollingFileAppender.class);
        RollingFileAppender newAppender = new RollingFileAppender();
        newAppender.setFile("target/created.log");
        TimeBasedRollingPolicy policy = new TimeBasedRollingPolicy();
        policy.setFileNamePattern("target/created.log.%d{yyyy-MM-dd-HH-mm}.gz");
        newAppender.setRollingPolicy(policy);
        newAppender.setLayout(new PatternLayout("%-15d{ISO8601} %-5p [[%t]] (%c) - %m%n"));
        newAppender.activateOptions();

        logger.addAppender(newAppender);
        // given

        logger.debug("XXX");
        // when

        // then
    }
}
