package com.bawi.services.calculator.status;

import java.util.TreeMap;

import org.apache.log4j.Logger;

public class About {
    private static final Logger LOGGER = Logger.getLogger(About.class);

    public About() {
        displaySystemProperties();
    }

    private void displaySystemProperties() {
        TreeMap<?, ?> treeMap = new TreeMap<Object, Object>(System.getProperties());
        LOGGER.debug(treeMap);
    }

    public void destroy() {
        LOGGER.error("Destroying ...");
    }
}
