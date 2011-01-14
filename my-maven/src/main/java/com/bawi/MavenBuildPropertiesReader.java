package com.bawi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public final class MavenBuildPropertiesReader {
    private static final String PROPERTIES_FILE_NAME = "maven.build.properties";
    private static final String PROPERTIES_FILE_PATH = "target/classes/" + PROPERTIES_FILE_NAME;

    private MavenBuildPropertiesReader() {
    }

    public static void main(String[] args) throws IOException {
        putToSystemProperties();
    }

    public static void putToSystemProperties() throws IOException {
        printSystemProperties(loadSystemPropertiesFromFile());
        System.out.println("Text");
        printSystemProperties(loadSystemPropertiesFromResource());
    }

    private static Properties loadSystemPropertiesFromFile() throws IOException {
        Properties systemProperties = new Properties();
        File file = new File(PROPERTIES_FILE_PATH);
        String fileAbsolutePath = file.getAbsolutePath();
        String projectBasedir = fileAbsolutePath.replaceAll(PROPERTIES_FILE_PATH, "");
        FileInputStream inStream;
        try {
            inStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File '" + fileAbsolutePath
                    + "' is generated during maven build process. Run maven build in '" + projectBasedir + "'.");
        }
        systemProperties.load(inStream);
        return systemProperties;
    }

    private static Properties loadSystemPropertiesFromResource() throws IOException {
        Properties systemProperties = new Properties();
        Class<MavenBuildPropertiesReader> clazz = MavenBuildPropertiesReader.class;
        ClassLoader classLoader = clazz.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream(PROPERTIES_FILE_NAME);
        if (resourceAsStream == null) {
            throw new RuntimeException("Could not load properties file: '" + PROPERTIES_FILE_NAME + "'");
        }
        systemProperties.load(resourceAsStream);
        return systemProperties;
    }

    private static void printSystemProperties(Properties systemProperties) {
        Set<String> stringPropertyNames = systemProperties.stringPropertyNames();
        for (String propertyName : stringPropertyNames) {
            System.out.println(propertyName + "=" + systemProperties.getProperty(propertyName));
        }
    }
}
