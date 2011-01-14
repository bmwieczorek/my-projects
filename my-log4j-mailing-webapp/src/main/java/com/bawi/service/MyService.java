package com.bawi.service;

import org.apache.log4j.Logger;

public class MyService {

    private static final Logger LOGGER = Logger.getLogger(MyService.class);

    public MyService() {
        for (int i = 0; i < 200; i++) {
            error();
            sleep(500);
        }
    }

    private void sleep(int sleepMillis) {
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void error() {
        LOGGER.debug("Hi");
        // throw new RuntimeException("My service exception");
    }

}
