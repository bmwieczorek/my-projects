package com.bawi.spring.boot.web;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        private static final Pattern DECRYPT_PATTERN = Pattern.compile("decrypt\\((.*?)\\)");

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        // PropertySources is Iterable over implementations of PropertySource: MapPropertySource[name=systemProperties, source=Map<String, Object>], applied for systemProperties and systemEnvironment
        // ConfigurationPropertySources[name=applicationConfigurationProperties, source=List<EnumerableCompositePropertySource>: [0]: PropertiesPropertySource[name=applicationConfig: classpath:/application.properties, source=Properties with server.port=8888

        for (PropertySource<?> propertySource : environment.getPropertySources()) {
            Map<String, Object> propertyOverrides = new LinkedHashMap<>();
            if (propertySource instanceof EnumerablePropertySource<?>) {
                EnumerablePropertySource<?> enumerablePropertySource = (EnumerablePropertySource<?>) propertySource;
                for (String key : enumerablePropertySource.getPropertyNames()) {
                    Object rawValue = propertySource.getProperty(key);
                    if (rawValue instanceof String) {
                        String value = decryptIfNeeded((String) rawValue);
                        propertyOverrides.put(key, value); // contain both replaced and unchanged property values
                    }
                }
            }
            if (!propertyOverrides.isEmpty()) {
                PropertySource<?> decodedProperties = new MapPropertySource("decrypted " + propertySource.getName(), propertyOverrides);
                environment.getPropertySources().addBefore(propertySource.getName(), decodedProperties);
            }
        }
    }

    private String decryptIfNeeded(String input) {
        if (input == null) {
            return null;
        }
        StringBuffer output = new StringBuffer();
        Matcher matcher = DECRYPT_PATTERN.matcher(input);
        while (matcher.find()) {
            String group = matcher.group(1);
            String replacement = null;
            try {
                replacement = PasswordUtils.decrypt(group);
            } catch (Exception e) {
                e.printStackTrace();
            }
            matcher.appendReplacement(output, replacement);
        }
        matcher.appendTail(output);
        return output.toString();
    }
}
