package com.bawi.devoxx.demo.one.listcontains;

import static java.util.Arrays.asList;

import java.util.List;

public class DeclarativeContainsBob {
    public static void main(String[] args) {
        List<String> names = asList("Bob", "Ben", "Bill");
        System.out.println("Contains Bob: " +  names.contains("Bob"));

        // less code - less probable for introducing a bug 
        // expressing what to do not how 
        // reply on underlying library/platform to do some kind of optimization
        // here no state mutation - always getting the same results
        // no mutating state but transforming state
        // immutability allows sharing and performance capability like e.g. String 
        // no side effects but pure which allows caching - always return the same value for the same input parameters
        
    }
}
