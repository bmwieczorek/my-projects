package com.bawi.maven;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.TreeMap;

public class ServiceRunner {

    // run via: export M2_REPO=~/dev/environment/apache-maven-2.0.9/localRepo; java -classpath
    // $M2_REPO/com/bawi/multi-module-maven-core/0.0.1-SNAPSHOT/multi-module-maven-core-0.0.1-SNAPSHOT.jar:$M2_REPO/com/bawi/multi-module-maven-war/0.0.1-SNAPSHOT/multi-module-maven-war-0.0.1-SNAPSHOT.jar
    // com.bawi.maven.ServiceRunner
    public static void main(String[] args) {
        loadProperties();
    }

    @SuppressWarnings("unchecked")
    private static void loadProperties() {
        InputStream stream = ClassLoader.getSystemResourceAsStream("service-runner.properties");
        Properties properties = new Properties();
        try {
            properties.load(stream);
            System.getProperties().putAll(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TreeMap treeMap = new TreeMap(System.getProperties());
        for (Object key : treeMap.keySet()) {
            System.out.println(key + "=" + treeMap.get(key));
        }
        System.out.println("Core service runner");
    }
}
