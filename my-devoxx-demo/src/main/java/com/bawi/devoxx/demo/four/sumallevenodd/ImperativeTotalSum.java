package com.bawi.devoxx.demo.four.sumallevenodd;

import static java.util.Arrays.asList;

import java.util.List;

public class ImperativeTotalSum {
    public static void main(String[] args) {
        List<Integer> numbers = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int sumOfAll = 0;
        for (int number : numbers) {
            sumOfAll = sumOfAll + number;
        }
        System.out.println(sumOfAll);

        int sumOfEven = 0;
        for (int number : numbers) {
            if (number % 2 == 0) {
                sumOfEven = sumOfEven + number;
            }
        }
        System.out.println(sumOfEven);

        int sumOfOdd = 0;
        for (int number : numbers) {
            if (number % 2 == 1) {
                sumOfOdd = sumOfOdd + number;
            }
        }
        System.out.println(sumOfOdd);
    }
}
