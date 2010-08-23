package com.bawi;

import static org.junit.Assert.assertThat;

import java.util.Enumeration;

import junit.framework.Assert;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hamcrest.Description;
import org.junit.Test;
import org.junit.matchers.TypeSafeMatcher;

public class RetrieveChildrenLoggersTest {

    @Test
    public void shouldRootBeAwareOfChildrenLoggersDeclaredInLog4jXml() {

        // given
        String declaredLoggerName = "com.bawi.LogMe";
        assertLoggerIsDeclaredInLog4jXml(declaredLoggerName);

        // when
        Enumeration<?> currentLoggers = LogManager.getCurrentLoggers();

        // then
        assertThat(currentLoggers, containLogger(declaredLoggerName));
    }

    @Test
    public void shouldRootBeNotAwareOfNotDeclaredChildrenLoggers() {

        // given
        String nonExistingLoggerName = "XXX";
        assertLoggerIsNotDeclaredInLog4jXml(nonExistingLoggerName);

        // when
        Enumeration<?> currentLoggers = LogManager.getCurrentLoggers();

        // then
        assertThat(currentLoggers, containNoSuchLogger(nonExistingLoggerName));
    }

    private void assertLoggerIsNotDeclaredInLog4jXml(String nonExistingLoggerName) {
        Assert.assertNull(LogManager.exists(nonExistingLoggerName));
    }

    private void assertLoggerIsDeclaredInLog4jXml(String declaredLoggerName) {
        Assert.assertNotNull(LogManager.exists(declaredLoggerName));
    }

    private TypeSafeMatcher<Enumeration<?>> containLogger(final String loggerName) {
        return new TypeSafeMatcher<Enumeration<?>>() {

            @Override
            public void describeTo(Description arg0) {
            }

            @Override
            public boolean matchesSafely(Enumeration<?> loggers) {
                while (loggers.hasMoreElements()) {
                    Logger logger = (Logger) loggers.nextElement();
                    if (loggerName.equals(logger.getName())) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    private TypeSafeMatcher<Enumeration<?>> containNoSuchLogger(final String loggerName) {
        return new TypeSafeMatcher<Enumeration<?>>() {

            @Override
            public void describeTo(Description arg0) {
            }

            @Override
            public boolean matchesSafely(Enumeration<?> loggers) {
                while (loggers.hasMoreElements()) {
                    Logger logger = (Logger) loggers.nextElement();
                    if (loggerName.equals(logger.getName())) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

}
