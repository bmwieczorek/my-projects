package com.bawi.services.runner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceRunner {

	public ServiceRunner() {
		String[] configLocations = new String[] {"cxf-context.xml", "services-context.xml"};
		new ClassPathXmlApplicationContext(configLocations);
	}
	
	public static void main(String[] args) {
		new ServiceRunner();
		while(true){
			try {
				Thread.sleep(Long.MAX_VALUE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
