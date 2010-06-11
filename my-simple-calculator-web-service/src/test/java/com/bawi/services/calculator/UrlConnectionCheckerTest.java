package com.bawi.services.calculator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UrlConnectionCheckerTest {

    @Test
    public void shouldReturnFalseWhenCannotConnect() throws Exception {
        // given
        UrlConnectionChecker connectionChecker = UrlConnectionChecker.createWithTimeOut(2);
        String nonExistentContext = "http://localhost:12345/dummy";

        // when
        boolean isServiceUp = connectionChecker.isUp(nonExistentContext);

        // then
        assertFalse(isServiceUp);
    }

    @Test
    public void shouldRetrunTrueWhenConnectedToServlet() throws Exception {
        // given
        UrlConnectionChecker connectionChecker = new UrlConnectionChecker();
        TestServletRunner runner = new TestServletRunner();
        runner.start();

        // when
        boolean isServiceUp = connectionChecker.isUp(runner.getUrl());

        // then
        runner.stop();
        assertTrue(isServiceUp);
    }
}
