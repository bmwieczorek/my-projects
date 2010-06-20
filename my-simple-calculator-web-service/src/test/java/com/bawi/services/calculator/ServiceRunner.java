package com.bawi.services.calculator;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceRunner {

    private static final Logger logger = Logger.getLogger(ServiceRunner.class);

    public ServiceRunner() {
        String[] configLocations = new String[] { "cxf-context.xml", "services-context.xml" };
        new ClassPathXmlApplicationContext(configLocations);
    }

    public static void main(String[] args) {
        logger.debug("Starting service runner");
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
