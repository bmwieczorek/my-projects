package com.bawi.maven;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.TreeMap;

public class ServiceRunner {

	public static void main(String[] args) {
		loadProperties();
	}

	private static void loadProperties() {
		InputStream stream = ClassLoader
				.getSystemResourceAsStream("service-runner.properties");
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
	}
}
