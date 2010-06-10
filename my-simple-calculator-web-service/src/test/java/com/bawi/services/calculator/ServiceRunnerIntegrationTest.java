package com.bawi.services.calculator;

import static junit.framework.Assert.assertTrue;

import java.io.IOException;
import java.net.ConnectException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.bawi.services.calculator.runner.ServiceRunner;

public class ServiceRunnerIntegrationTest {

    private static Logger logger = Logger.getLogger(ServiceRunnerIntegrationTest.class);
    private static final String URL = "http://localhost:7890/calculator?wsdl";
    private static final int TIMEOUT_SECONDS = 10;
    private static final int RETRY_INTERVAL_SECONDS = 1;
    private HttpClient client = new HttpClient();

    @Test
    public void shouldStartServiceRunner() throws Exception {
        Thread serviceRunner = createServiceRunnerThread();
        serviceRunner.start();
        assertTrue(isServiceUp(URL));
    }

    private boolean isServiceUp(String url) throws HttpException, IOException, InterruptedException {
        GetMethod method = new GetMethod(url);
        for (int i = 0; i < TIMEOUT_SECONDS; i = i + RETRY_INTERVAL_SECONDS) {
            int statusCode;
            try {
                logger.debug("Trying " + URL + " ...");
                statusCode = client.executeMethod(method);
                if (HttpStatus.SC_OK == statusCode) {
                    logger.debug("Successfully connected to " + URL + "!");
                    return true;

                }
            } catch (ConnectException e) {
                logger.debug("Failed to establish connection to " + URL + " (" + e.getMessage()
                        + "). Retrying in " + RETRY_INTERVAL_SECONDS + " second(s).");
                Thread.sleep(1000 * RETRY_INTERVAL_SECONDS);
            }
        }
        return false;
    }

    private Thread createServiceRunnerThread() {
        Thread serviceRunner = new Thread(new Runnable() {
            @Override
            public void run() {
                ServiceRunner.main(null);
            }
        });
        return serviceRunner;
    }

}
