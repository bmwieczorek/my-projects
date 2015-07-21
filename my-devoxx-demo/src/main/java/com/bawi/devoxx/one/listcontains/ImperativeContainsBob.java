package com.bawi.devoxx.one.listcontains;

import static java.util.Arrays.asList;

import java.util.List;

public class ImperativeContainsBob { 
    public static void main(String[] args) {
        List<String> names = asList("Bob", "Ben", "Bill");
        boolean found = false;
        for (String name : names) {
            if ("Bob".equals(name)) {
                found = true;
                break;
            }
        }
        System.out.println("Contains Bob: " + found);
        
        // mutating the state of found indicator
        // state mutation especially global state makes it harder to fix problems
        // when state is mutated the order of execution can matter so the results can be different
    }
}
