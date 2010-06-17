package com.bawi.services.calculator;

import org.junit.Assert;
import org.junit.Test;

import com.bawi.itests.connection.UrlConnectionChecker;
import com.bawi.services.calculator.runner.ServiceRunner;

public class ServiceRunnerIntegrationTest {

    private UrlConnectionChecker connectionChecker = UrlConnectionChecker.createWithTimeOut(5);

    @Test
    public void shouldStartServiceRunner() throws Exception {
        // given
        Thread serviceRunner = createServiceRunnerThread();
        serviceRunner.start();

        // when
        boolean isServiceUp = connectionChecker.isUp("http://localhost:7890/calculator?wsdl");

        // then
        Assert.assertTrue(isServiceUp);
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
