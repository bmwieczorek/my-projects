package com.bawi.services.calculator.runner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class ServiceRunner {

    private ServiceRunner() {
        String[] configLocations = new String[] { "cxf-context.xml", "services-context.xml" };
        new ClassPathXmlApplicationContext(configLocations);
    }

    public static void main(String[] args) {
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
