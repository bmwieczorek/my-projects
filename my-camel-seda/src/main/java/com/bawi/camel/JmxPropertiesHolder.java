package com.bawi.camel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JmxPropertiesHolder {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmxPropertiesHolder.class);

    private int maximumRequestCount;

    public int getMaximumRequestCount() {
        return maximumRequestCount;
    }

    public void setMaximumRequestCount(int maximumRequestCount) {
        LOGGER.info("Setting maximumRequestCount={}", maximumRequestCount);
        this.maximumRequestCount = maximumRequestCount;
    }

    @Override
    public String toString() {
        return "JmxPropertiesHolder [maximumRequestCount=" + maximumRequestCount + "]";
    }

}
