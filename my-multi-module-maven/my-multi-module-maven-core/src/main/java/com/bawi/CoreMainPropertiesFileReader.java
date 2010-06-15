package com.bawi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class CoreMainPropertiesFileReader {

    public static void main(String[] args) throws IOException {
        new CoreMainPropertiesFileReader();
    }

    public CoreMainPropertiesFileReader() throws IOException {
        Properties properties = readPropertiesFromResource("my-core-main.properties");
        printSystemProperties(properties);
    }

    public static Properties readPropertiesFromResource(String propertiesFileName) throws IOException {
        Properties properties = new Properties();
        Class<CoreMainPropertiesFileReader> clazz = CoreMainPropertiesFileReader.class;
        ClassLoader classLoader = clazz.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream(propertiesFileName);
        if (resourceAsStream == null) {
            throw new RuntimeException("Could not load properties file: '" + propertiesFileName + "'");
        }
        properties.load(resourceAsStream);
        return properties;
    }

    private static void printSystemProperties(Properties systemProperties) {
        Set<String> stringPropertyNames = systemProperties.stringPropertyNames();
        for (String propertyName : stringPropertyNames) {
            System.out.println(propertyName + "=" + systemProperties.getProperty(propertyName));
        }
    }
}
