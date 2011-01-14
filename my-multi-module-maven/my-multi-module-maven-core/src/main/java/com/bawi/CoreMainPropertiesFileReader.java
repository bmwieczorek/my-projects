package com.bawi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class CoreMainPropertiesFileReader {

    private CoreMainPropertiesFileReader() {
    }

    public static void main(String[] args) throws IOException {
        System.out.println(readPropertiesFromResource("my-core-main.properties"));
        System.out.println(readPropertiesFromFile("src/test/resources/com/bawi/my-com-bawi-core-test.properties"));
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

    public static Properties readPropertiesFromFile(String filePath) throws IOException {
        Properties properties = new Properties();
        File file = new File(filePath);
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(file.getAbsolutePath() + " doesn't exist: " + e.getMessage());
        }
        properties.load(fileReader);
        return properties;
    }

}
