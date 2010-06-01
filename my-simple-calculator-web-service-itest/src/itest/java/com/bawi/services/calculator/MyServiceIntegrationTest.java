package com.bawi.services.calculator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.junit.Test;

public class MyServiceIntegrationTest {
    private Logger logger = Logger.getLogger(MyServiceIntegrationTest.class);
    private String url = "http://localhost:8080/forwarder/hello.jsp";

    @Test
    public void shouldForward() throws Exception {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        int statusCode = client.executeMethod(method);
        assertEquals(HttpStatus.SC_OK, statusCode);
        String responseBody = method.getResponseBodyAsString();
        logger.debug("responseBody=" + responseBody);
        assertTrue(responseBody.contains("Hello World (my simple calculator web service)!"));
    }
}
