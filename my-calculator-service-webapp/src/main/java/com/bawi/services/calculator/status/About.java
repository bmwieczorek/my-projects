package com.bawi.services.calculator.status;

import java.util.TreeMap;

import org.apache.log4j.Logger;

public class About {
    private static final Logger logger = Logger.getLogger(About.class);

    public About() {
        displaySystemProperties();
    }

    private void displaySystemProperties() {
        TreeMap<?, ?> treeMap = new TreeMap<Object, Object>(System.getProperties());
        logger.debug(treeMap);
    }

    public void destroy() {
        logger.error("Destroying ...");
    }
}
