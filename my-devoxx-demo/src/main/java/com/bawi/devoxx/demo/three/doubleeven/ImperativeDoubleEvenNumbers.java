package com.bawi.devoxx.demo.three.doubleeven;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

public class ImperativeDoubleEvenNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> doubledEvenNumbers = new ArrayList<>();
        for (Integer number : numbers) { // hard to parallelize
            if (number % 2 == 0) {
                doubledEvenNumbers.add(number * 2);
            }
        }
        System.out.println(doubledEvenNumbers);
    }
}
