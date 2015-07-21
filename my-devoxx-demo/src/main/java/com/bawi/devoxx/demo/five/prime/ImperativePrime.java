package com.bawi.devoxx.demo.five.prime;

import static java.util.Arrays.asList;

import java.util.List;

public class ImperativePrime {
    public static void main(String[] args) {
        List<Integer> numbers = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (int number : numbers) {
            if (isDivisible(number)) {
                System.out.println(number);
            }
        }
    }

    private static boolean isDivisible(int number) {
        for (int i = 2; i < number; i++) {
            //System.out.println("Is divisible" + number);
            if (number % i == 0) {
                return false;
            }
        }
        return number > 1;
    }
}
