package com.bawi.services.calculator;

import static junit.framework.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.bawi.services.calculator.runner.ServiceRunner;

public class ServiceRunnerIntegrationTest {

    private UrlConnectionChecker urlConnectionChecker = new UrlConnectionChecker();

    static Logger logger = Logger.getLogger(ServiceRunnerIntegrationTest.class);
    private static final String url = "http://localhost:7890/calculator?wsdl";

    @Test
    public void shouldStartServiceRunner() throws Exception {
        Thread serviceRunner = createServiceRunnerThread();
        serviceRunner.start();
        assertTrue(urlConnectionChecker.isStatusCodeOK(url));
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
