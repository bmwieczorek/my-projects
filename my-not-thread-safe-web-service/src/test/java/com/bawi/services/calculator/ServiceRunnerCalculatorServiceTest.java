package com.bawi.services.calculator;

import org.junit.Before;

public class ServiceRunnerCalculatorServiceTest extends AbstractThreadSafetyCalculatorServiceTest {

    @Before
    public void startSeviceRunner() {
        Thread serviceRunner = createServiceRunnerThread();
        serviceRunner.setDaemon(true);
        serviceRunner.start();
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
