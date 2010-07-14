package com.bawi.test.assertions;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

public class AssertTest {

    @Test
    public void shouldReturnWithTimeout() {

        Condition httpConnectionCondition = new Condition() {

            @Override
            public boolean execute() {
                HttpClient client = new HttpClient();
                String url = "http://www.google.com";
                GetMethod method = new GetMethod(url);
                try {
                    return HttpStatus.SC_OK == client.executeMethod(method);
                } catch (Exception e) {
                    return false;
                }
            }
        };

        Assert.retriedAssert().withinInterval(10).matches(httpConnectionCondition);
    }
}
