package com.bawi.myspringapp;

import org.apache.log4j.Logger;

import com.bawi.myspringapp.context.ApplicationBeanProvider;

public class MyService {

    private static final Logger logger = Logger.getLogger(MyService.class);

    private final CacheProvider cacheProvider;

    public MyService() {
        cacheProvider = getCacheProviderFromApplicationContext();
        useCache();
    }

    MyService(CacheProvider cacheProvider) {
        this.cacheProvider = cacheProvider;
    }

    private void useCache() {
        Cache cache = cacheProvider.getCache();
        logger.debug("Created cache " + cache);
    }

    private CacheProvider getCacheProviderFromApplicationContext() {
        return ApplicationBeanProvider.getBean(CacheProvider.class);
    }

    // used in tests
    CacheProvider getCacheProvider() {
        return cacheProvider;
    }

}
