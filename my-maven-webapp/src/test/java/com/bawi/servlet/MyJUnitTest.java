package com.bawi.servlet;

import org.apache.log4j.Logger;
import org.junit.Test;

public class MyJUnitTest {
    private Logger logger = Logger.getLogger(MyJUnitTest.class);

    @Test
    public void MyTestCase() throws Exception {
        logger.debug("My JUnit test");
    }
}
