package com.bawi.mywebapp;

import java.util.TreeMap;

import org.apache.log4j.Logger;

public class About {
	private static final Logger logger = Logger.getLogger(About.class);
	
	public About() {
		displaySystemProperties();
		try {
			throw new AboutException("My exception!!!");
		} catch (AboutException e) {
			logger.error(createText());
		}
	}

    private String createText() {
        return "Throwing my exception !!!";
    }
	
	@SuppressWarnings("unchecked")
	private void displaySystemProperties() {
		TreeMap treeMap = new TreeMap(System.getProperties());
        logger.debug(treeMap);
		// for (Object key : treeMap.keySet()) {
		// logger.debug(key + "=" + treeMap.get(key));
		// }
	}
	
	public void destroy(){
		logger.error("Destroying ...");
	}
}
