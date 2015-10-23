package com.bawi.devoxx.demo.eight.lazy.performance;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ImperativeCustomerAnalysis {
    static LongRunningAccountBalanceRetriever longRunningAccountBalanceRetriever = new LongRunningAccountBalanceRetriever();

    public static void main(String[] args) {
        List<String> names = asList("Bob", "Bill", "Joe", "Mike", "Kim", "Lucy");
        long start = System.currentTimeMillis();

        List<Customer> customers = getCustomers(names);

        // 1. collection single usage, no laziness gain: duration 6s
        printFirstMillionaire(customers);

        // 2. collection reuse: 6s
        // printFirstMillionaire(customers);
        // printFirstCustomer(customers);

        long stop = System.currentTimeMillis();
        System.out.println("Took ms: " + (stop - start));
    }

    private static void printFirstCustomer(List<Customer> customers) {
        System.out.println("First customer: " + customers.get(0));
    }

    private static void printFirstMillionaire(List<Customer> customers) {
        for (Customer customer : customers) {
            if (customer.getBalance() >= Double.valueOf(1000000)) {
                System.out.println("We have at least millionaire: " + customer);
                break;
            }
        }
    }

    private static List<Customer> getCustomers(List<String> names) {
        List<Customer> customers = new ArrayList<>();
        int counter = 0;
        for (String name : names) {
            Double balance = longRunningAccountBalanceRetriever.getAccountBallance(counter); // takes 1s to retrieve
            Customer customer = new Customer(name, counter++, balance);
            customers.add(customer);
        }
        return customers;
    }
}
