package com.bawi.servlet;

import org.apache.log4j.Logger;
import org.junit.Test;

public class MyJUnitTest {
    private static final Logger LOGGER = Logger.getLogger(MyJUnitTest.class);

    @Test
    public void myTestCase() throws Exception {
        LOGGER.debug("My JUnit test");
    }
}
