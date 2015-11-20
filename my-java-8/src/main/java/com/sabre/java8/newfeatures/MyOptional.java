package com.sabre.java8.newfeatures;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MyOptional {

    public static void main(String[] args) {
        MyOptional m = new MyOptional();
        List<Integer> list24 = Arrays.asList(2, 4);
        System.out.println(m.findFirstEven(list24)); // 2
        System.out.println(m.findFistEvenOptional(list24)); // Optional[2]
        System.out.println(m.findFistEvenOptional(list24).isPresent()); // true
        System.out.println(m.findFistEvenOptional(list24).orElse(-1)); // 2
        m.findFistEvenOptional(list24).ifPresent(System.out::println); // 2
        System.out.println(m.findFistEvenOptional(list24).get()); // 2

        List<Integer> list13 = Arrays.asList(1, 3);
        System.out.println(m.findFirstEven(list13)); // null
        System.out.println(m.findFistEvenOptional(list13)); // Optional.emtpy
        System.out.println(m.findFistEvenOptional(list13).isPresent()); // false
        System.out.println(m.findFistEvenOptional(list13).orElse(-1)); // -1
        m.findFistEvenOptional(list13).ifPresent(System.out::println); // not printed
        System.out.println(m.findFistEvenOptional(list13).get()); // java.util.NoSuchElementException: No value present
    }

    public Optional<Integer> findFistEvenOptional(List<Integer> numbers) {
        return numbers.stream().filter(n -> n % 2 == 0).findFirst();
    }

    public Integer findFirstEven(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                return number;
            }
        }
        return null;
    }
}
