package com.bawi.myspringapp;

public class CacheProvider {

    private Cache cache;

    public CacheProvider(Cache cache) {
        this.cache = cache;
    }

    public Cache getCache() {
        return cache;
    }
}
