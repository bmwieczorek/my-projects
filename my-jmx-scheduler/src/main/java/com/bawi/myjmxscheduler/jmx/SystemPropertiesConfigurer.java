package com.bawi.myjmxscheduler.jmx;

import java.util.Properties;
import java.util.Set;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(objectName = "bean.com.bawi:name=SystemPropertiesConfigurer", description = "A tool to view/set/override/remove system properties")
public class SystemPropertiesConfigurer {

    private String key;

    private String value;

    @ManagedAttribute
    public String getKey() {
        return key;
    }

    @ManagedAttribute
    public void setKey(String key) {
        this.key = key;
    }

    @ManagedAttribute
    public String getValue() {
        return value;
    }

    @ManagedAttribute
    public void setValue(String value) {
        this.value = value;
    }

    @ManagedOperation(description = "add/override property")
    public void addProperty() {
        if (!isEmpty(key)) {
            Properties properties = System.getProperties();
            properties.setProperty(key, value);
        }
    }

    @ManagedOperation(description = "remove property")
    public void removeProperty() {
        if (!isEmpty(key)) {
            Properties properties = System.getProperties();
            if (properties.containsKey(key)) {
                properties.remove(key);
            }
        }
    }

    @ManagedOperation(description = "List system properties")
    public String showSystemProperties() {
        Properties properties = System.getProperties();
        Set<Object> keySet = properties.keySet();
        StringBuilder builder = new StringBuilder("\n");
        for (Object key : keySet) {
            builder.append(key + "=" + properties.get(key) + "\n");
        }
        return builder.toString();
    }

    private static boolean isEmpty(String value) {
        return (value == null || value.length() == 0) ? true : false;
    }
}
