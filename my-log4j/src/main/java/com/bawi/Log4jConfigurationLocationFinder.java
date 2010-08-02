package com.bawi;

import java.net.URL;

public class Log4jConfigurationLocationFinder {

    public static String getLog4jConfigurationLocation() {
        String log4jConfigurationProperty = System.getProperty("log4j.configuration");
        if (log4jConfigurationProperty != null) {
            return log4jConfigurationProperty;
        }
        URL resource = null;
        resource = Log4jConfigurationLocationFinder.class.getClassLoader().getResource("log4j.xml");
        if (resource != null) {
            return resource.getFile();
        }

        resource = Log4jConfigurationLocationFinder.class.getClassLoader().getResource("log4j.properties");
        if (resource != null) {
            return resource.getFile();
        }

        return null;
    }

}
