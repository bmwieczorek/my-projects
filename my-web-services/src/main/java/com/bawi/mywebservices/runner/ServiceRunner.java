package com.bawi.mywebservices.runner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.TreeMap;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class ServiceRunner {

    private ServiceRunner() {
        String[] configLocations = new String[] { "cxf-context.xml", "services-context.xml" };
        new ClassPathXmlApplicationContext(configLocations);
        loadProperties();

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

    private void loadProperties() {
        InputStream stream = ClassLoader.getSystemResourceAsStream("service-runner.properties");
        Properties properties = new Properties();
        try {
            properties.load(stream);
            System.getProperties().putAll(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TreeMap<?, ?> treeMap = new TreeMap<Object, Object>(System.getProperties());
        for (Object key : treeMap.keySet()) {
            System.out.println(key + "=" + treeMap.get(key));
        }
    }
}
