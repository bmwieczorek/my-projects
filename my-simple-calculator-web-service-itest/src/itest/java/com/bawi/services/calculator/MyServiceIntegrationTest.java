package com.bawi.services.calculator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.junit.Test;

public class MyServiceIntegrationTest {
    private Logger logger = Logger.getLogger(MyServiceIntegrationTest.class);
    private String url = "http://localhost:8080/forwarder/do/hello.jsp";

    @Test
    public void shouldForwardInsideContext() throws Exception {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        method.setRequestHeader("myHeader", "header-set-in-tests");
        method.setQueryString(new NameValuePair[] { new NameValuePair("myParameter", "local") });
        int statusCode = client.executeMethod(method);
        assertEquals(HttpStatus.SC_OK, statusCode);
        String responseBody = method.getResponseBodyAsString();
        logger.debug("responseBody=" + responseBody);
        assertTrue(responseBody.contains("Hello World (my request forwarder)!"));
    }

    @Test
    public void shouldForwardToOtherContext() throws Exception {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        method.setRequestHeader("myHeader", "header-set-in-tests");
        method.setQueryString(new NameValuePair[] { new NameValuePair("myParameter", "cross") });
        int statusCode = client.executeMethod(method);
        assertEquals(HttpStatus.SC_OK, statusCode);
        String responseBody = method.getResponseBodyAsString();
        logger.debug("responseBody=" + responseBody);
        assertTrue(responseBody.contains("Hello World (my simple calculator web service)!"));
    }
}
