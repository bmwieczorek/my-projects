package com.bawi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class MavenBuildPropertiesReader {
    private final static String MAVEN_BUILD_PROPERTIES_FILE_PATH = "target/classes/maven.build.properties";

    public static void main(String[] args) throws IOException {
        putToSystemProperties();
    }

    public static void putToSystemProperties() throws IOException {
        // Properties systemProperties = loadSystemPropertiesFromFile();
        Properties systemProperties = loadSystemPropertiesFromResource("maven.build.properties");
        printSystemProperties(systemProperties);

    }

    private static Properties loadSystemPropertiesFromFile() throws FileNotFoundException, IOException {
        Properties systemProperties = System.getProperties();
        File file = new File(MAVEN_BUILD_PROPERTIES_FILE_PATH);
        String fileAbsolutePath = file.getAbsolutePath();
        String projectBasedir = fileAbsolutePath.replaceAll("/" + MAVEN_BUILD_PROPERTIES_FILE_PATH, "");
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

    private static Properties loadSystemPropertiesFromResource(String resourceFile) throws IOException {
        Properties systemProperties = System.getProperties();
        // MavenBuildPropertiesReader.class.getClassLoader().get
        InputStream resourceAsStream = MavenBuildPropertiesReader.class.getClassLoader().getResourceAsStream(
                resourceFile);
        if (resourceAsStream != null) {
            throw new RuntimeException("Could not load properties file: '" + resourceFile + "'");
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
