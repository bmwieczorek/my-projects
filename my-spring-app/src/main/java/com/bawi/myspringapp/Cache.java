package com.bawi.myspringapp;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private Map<String, Integer> nameToAgeMapping = new HashMap<String, Integer>();

    public boolean containsPrice(String name) {
        return nameToAgeMapping.get(name) != null;
    }

    public int getPrice(String name) {
        return nameToAgeMapping.get(name);
    }

    public int putPrice(String name, int price) {
        return nameToAgeMapping.put(name, price);
    }

}
