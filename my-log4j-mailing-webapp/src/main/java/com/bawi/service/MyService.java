package com.bawi.service;

import org.apache.log4j.Logger;

public class MyService {

    private static final Logger logger = Logger.getLogger(MyService.class);

    public MyService() {
        logger.debug("Hi");
        throw new RuntimeException("My service exception");
    }

}
