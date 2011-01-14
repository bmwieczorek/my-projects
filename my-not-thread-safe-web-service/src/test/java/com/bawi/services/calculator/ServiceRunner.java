package com.bawi.services.calculator;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class ServiceRunner {

    private static final Logger LOGGER = Logger.getLogger(ServiceRunner.class);

    private ServiceRunner() {
        String[] configLocations = new String[] { "cxf-context.xml", "services-context.xml" };
        new ClassPathXmlApplicationContext(configLocations);
    }

    public static void main(String[] args) {
        LOGGER.debug("Starting service runner");
        new ServiceRunner();
        while (true) {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
