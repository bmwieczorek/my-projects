package com.bawi.services.calculator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.junit.Test;

public class MyServiceIntegrationTest {
    private Logger logger = Logger.getLogger(MyServiceIntegrationTest.class);
    private HttpClient httpClient = new HttpClient();
    private String url = "http://localhost:8080/forwarder/hello.jsp";

    @Test
    public void shouldForward() throws Exception {
        GetMethod method = new GetMethod(url);
        int statusCode = httpClient.executeMethod(method);
        assertEquals(HttpStatus.SC_OK, statusCode);
        String responseBody = method.getResponseBodyAsString();
        logger.debug("responseBody=" + responseBody);
        assertTrue(responseBody.contains("Hello World (my simple calculator web service)!"));
    }

    @Test
    public void shouldPreserveHeadersForCrossContext() throws Exception {
        GetMethod method = new GetMethod(url);
        String value = "Ania";
        method.setRequestHeader(new Header("myHeader", value));
        HttpMethodParams params = new HttpMethodParams();
        params.setParameter("myParameter", "CROSS");
        method.setParams(params);
        int statusCode = httpClient.executeMethod(method);
        assertEquals(HttpStatus.SC_OK, statusCode);
        // jsp page explicitly prints value of h1 header
        String responseBody = method.getResponseBodyAsString();
        logger.debug("responseBody=" + responseBody);
        assertTrue(responseBody.contains("Request header : " + value));
    }

    @Test
    public void shouldPreserveHeadersForTheSameContext() throws Exception {
        GetMethod method = new GetMethod(url);
        String value = "Ania";
        method.setRequestHeader(new Header("myHeader", value));
        HttpMethodParams params = new HttpMethodParams();
        params.setParameter("myParameter", "LOCAL");
        method.setParams(params);
        int statusCode = httpClient.executeMethod(method);
        assertEquals(HttpStatus.SC_OK, statusCode);
        // jsp page explicitly prints value of h1 header
        String responseBody = method.getResponseBodyAsString();
        logger.debug("responseBody=" + responseBody);
        assertTrue(responseBody.contains("Request header : " + value));
    }

}
