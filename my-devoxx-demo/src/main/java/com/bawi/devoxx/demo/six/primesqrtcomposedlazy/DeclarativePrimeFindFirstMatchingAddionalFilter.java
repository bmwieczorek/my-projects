package com.bawi.devoxx.demo.six.primesqrtcomposedlazy;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DeclarativePrimeFindFirstMatchingAddionalFilter {

    public static void main(String[] args) {

        System.out.println(
        Stream.iterate(5, i -> i + 1) 
            .filter(DeclarativePrimeFindFirstMatchingAddionalFilter::isPrime) // composition of functions 
            .filter(n -> isSqrtGreaterThan5(n)) // only prime numbers go to the second filter
            .map(n -> { System.out.println("map:" + n); return n; }) // lazy evaluation only for the first one matching the filters
            .findFirst() // terminal operation
            .get()
        );

    }

    private static boolean isSqrtGreaterThan5(int number) {
        System.out.println("isSqrtGreaterThan5: " + number);
        return Math.sqrt(number) > 5;
    }

    private static boolean isPrime(int number) {
        System.out.println("isPrime: " + number);
        return number > 1 && 
                IntStream.range(2, number)
                    .noneMatch(i -> number % i == 0);
    }

}