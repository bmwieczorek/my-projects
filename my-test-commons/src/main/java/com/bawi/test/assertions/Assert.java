package com.bawi.test.assertions;

import org.apache.log4j.Logger;

public class Assert {
    private static final Logger LOGGER = Logger.getLogger(Assert.class);
    private static final int RETRY_INTERVAL = 1;
    private int timeout;

    public void matches(Condition condition) {
        LOGGER.debug("Starting retried assertion with retry interval " + RETRY_INTERVAL + " second(s) and timeout "
                + timeout + " second(s).");
        for (int i = 0; i < timeout; i = i + RETRY_INTERVAL) {
            if (condition.execute()) {
                return;
            }
            sleepSeconds(RETRY_INTERVAL);
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
