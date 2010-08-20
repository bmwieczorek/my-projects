package com.bawi.services.calculator.threadsafety;

import org.junit.Before;

import com.bawi.services.calculator.ServiceRunner;

public class ServiceRunnerThreadSafetyTest extends AbstractThreadSafetyCalculatorServiceTest {

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
