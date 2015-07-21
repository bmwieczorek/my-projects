package com.bawi.devoxx.demo.five.prime;

import java.util.stream.IntStream;

public class DeclarativePrime {

    public static void main(String[] args) {
//        List<Integer> numbers = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        for (int number : numbers) {
//            if (isPrime(number)) {
//                System.out.println(number);
//            }
//        }

//        IntStream.range(1, 10)
//            .filter(n -> isPrime(n))
//            .forEach(n -> System.out.println(n));

        IntStream.range(1, 10)
            .filter(DeclarativePrime::isPrime)
            .forEach(System.out::println);

    }

    private static boolean isPrime(int number) {
//        return number > 1 && !
//        IntStream.range(2, number)
//            .filter(i -> number % i == 0)
//            .findAny()
//            .isPresent();
        return number > 1 && 
                IntStream.range(2, number)
                    .noneMatch(i -> number % i == 0);
    }
}
