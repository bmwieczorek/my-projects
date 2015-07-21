package com.bawi.devoxx.demo.three.doubleeven;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeclarativeDoubleEvenNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(numbers.
                stream().
                // parallelStream(). // easy to parallelize 
                filter(n -> n % 2 == 0).
                map(n -> n * 2).
                collect(Collectors.toList()));
    }
}
