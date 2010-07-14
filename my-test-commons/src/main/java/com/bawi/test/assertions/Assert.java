package com.bawi.test.assertions;

import org.apache.log4j.Logger;

public class Assert {
    private static final int retryInterval = 1;
    private int timeout;
    private static Logger logger = Logger.getLogger(Assert.class);

    public void matches(Condition condition) {
        logger.debug("Starting retried assertion with retry interval " + retryInterval
                + " second(s) and timeout " + timeout + " second(s).");
        for (int i = 0; i < timeout; i = i + retryInterval) {
            if (condition.execute()) {
                return;
            }
            sleepSeconds(retryInterval);
        }
    }

    public static Assert retriedAssert() {
        return new Assert();
    }

    private static void sleepSeconds(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Assert withinInterval(int timeout) {
        this.timeout = timeout;
        return this;
    }

}
