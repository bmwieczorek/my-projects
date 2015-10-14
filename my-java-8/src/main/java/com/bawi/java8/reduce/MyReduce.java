package com.bawi.java8.reduce;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MyReduce {
    public static void main(String[] args) {
        
        List<Integer> numbers = asList(1, 2, 3, 4);
        Optional<Integer> resultOptional = numbers
                .stream()
                .reduce((x, y) -> x * y);
        resultOptional.ifPresent(System.out::println);
        // 24, note optional is returned from reduce

        
        Integer result = numbers
                .stream()
                .reduce(1, (x, y) -> x * y);
        System.out.println(result);
        // 24, note Integer is returned from reduce        
        
        
        // reduce to map with key string (letter) and value (count of each letter), note alternative for that is: Collectors.groupingBy(s -> s, Collections.counting())
        Map<String, Integer> reduce = Arrays.asList("a", "b", "a", "c", "b", "a")
            .stream()
            //.parallelStream()
            .reduce(new HashMap<String, Integer>(), 
                    (map, string) -> { 
                        map.compute(string, (k, v) -> map.containsKey(k) ? v + 1 : 1);
                        System.out.println("In accumulator: " + Thread.currentThread().getName());
                        return map; 
                    }, 
                    (map1, map2) -> {  
                        map1.putAll(map2);
                        System.out.println("In combiner: " + Thread.currentThread().getName()); // only used for parallel stream
                        return map1; 
                    }
            );
        System.out.println(reduce);
        /*
        // (sequential) stream output: (accumulator used for each element, combiner not used)
        In accumulator: main
        In accumulator: main
        In accumulator: main
        In accumulator: main
        In accumulator: main
        In accumulator: main
        {a=3, b=2, c=1}
        
        // parallel stream output: (accumulator used for each element, combiner used for merging)
        In accumulator: main
        In accumulator: main
        In accumulator: main
        In combiner: main
        In combiner: main
        In accumulator: ForkJoinPool.commonPool-worker-1
        In accumulator: ForkJoinPool.commonPool-worker-1
        In combiner: ForkJoinPool.commonPool-worker-1
        In accumulator: ForkJoinPool.commonPool-worker-1
        In combiner: ForkJoinPool.commonPool-worker-1
        In combiner: ForkJoinPool.commonPool-worker-1
        {a=3, b=2, c=1}
        */
    }
}
