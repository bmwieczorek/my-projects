package com.bawi.services.calculator.status;

import java.util.TreeMap;

import org.apache.log4j.Logger;

public class About {
    private static final Logger logger = Logger.getLogger(About.class);

    public About() {
        displaySystemProperties();
    }

    @SuppressWarnings("unchecked")
    private void displaySystemProperties() {
        TreeMap treeMap = new TreeMap(System.getProperties());
        logger.debug(treeMap);
    }

    public void destroy() {
        logger.error("Destroying ...");
    }
}
