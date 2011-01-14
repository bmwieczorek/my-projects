package com.bawi.servicemix;

import java.util.Properties;
import java.util.TreeSet;

public class Client {

    public Client() {
        Properties properties = System.getProperties();
        for (String key : new TreeSet<String>(properties.stringPropertyNames())) {
            System.out.println(key + "=" + properties.getProperty(key));
        }
    }
}
