package com.bawi.spring.boot.web;

import java.util.List;
import java.util.*;

public class ReduceStream {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("A", "B", "C", "A");
        String reducedString = strings
                .stream()
                .reduce("", (x, y) -> x + y);
        System.out.println(reducedString);

        reducedString = strings
                .stream()
                .reduce((x, y) -> x + y)
                .get();
        System.out.println(reducedString);

        Map<String, Integer> reducedMap = strings
                .stream()
                .reduce(new HashMap<>(),
                        (map, key) -> {
                            map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1);
                            return map;
                        },
                        (map1, map2) -> {
                            map2.keySet()
                                .stream()
                                .forEach(key -> map1.put(key, map1.containsKey(key) ? map1.get(key) + map2.get(key) : map2.get(key)));
                            return map1;
                        });
        System.out.println(reducedMap);

        reducedMap = new HashMap<>();
        for (String key : strings) {
            reducedMap.put(key, reducedMap.containsKey(key) ? reducedMap.get(key) + 1 : 1);
        }
        System.out.println(reducedMap);
    }
}
