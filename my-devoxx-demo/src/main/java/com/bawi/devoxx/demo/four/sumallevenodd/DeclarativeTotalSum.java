package com.bawi.devoxx.demo.four.sumallevenodd;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.Predicate;

public class DeclarativeTotalSum {

    public static void main(String[] args) {
        
        List<Integer> numbers = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(getSum(numbers, n ->  true));
        System.out.println(getSum(numbers, n ->  n % 2 == 0));
        System.out.println(getSum(numbers, n ->  n % 2 == 1)); 
    }

    private static int getSum(List<Integer> numbers, Predicate<Integer> predicate) {
//        int sumOfAll = 0;
//        for (int number : numbers) {
//            if (predicate.test(number)) {
//                sumOfAll = sumOfAll + number;
//            }
//        }
//        return sumOfAll;
        return numbers
                .stream()
                .filter(predicate)
                .mapToInt(n -> n)
                .sum();
    }
    
    // remove duplication
    // apply strategy pattern at the function level
}
