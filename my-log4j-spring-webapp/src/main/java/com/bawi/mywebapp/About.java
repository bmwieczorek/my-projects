package com.bawi.mywebapp;

import java.util.TreeMap;

import org.apache.log4j.Logger;

public class About {
    private static final Logger LOGGER = Logger.getLogger(About.class);

    public About() {
        displaySystemProperties();
        try {
            throw new AboutException("My exception!!!");
        } catch (AboutException e) {
            LOGGER.error(createText());
        }
    }

    private String createText() {
        return "Throwing my exception !!!";
    }

    private void displaySystemProperties() {
        TreeMap<?, ?> treeMap = new TreeMap<Object, Object>(System.getProperties());
        LOGGER.debug(treeMap);
        // for (Object key : treeMap.keySet()) {
        // logger.debug(key + "=" + treeMap.get(key));
        // }
    }

    public void destroy() {
        LOGGER.error("Destroying ...");
    }
}
