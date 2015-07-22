package com.bawi.devoxx.demo.seven.lazy;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

public class ImperativeCustomerAnalysis {
    static AccountBalanceRetriever accountBalanceRetriever = new AccountBalanceRetriever();

    public static void main(String[] args) {
        List<String> names = asList("Bob", "Bill", "Joe", "Mike", "Kim", "Lucy");
        long start = System.currentTimeMillis();

        List<Customer> customers = getCustomers(names);

        // printFirstMillionaire(customers);

        printFirstMillionaire(customers);
        printFirstCustomer(customers);

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
        int id = 0;
        for (String name : names) {
            Double balance = accountBalanceRetriever.getAccountBallance(id);
            Customer customer = new Customer(name, id++, balance);
            customers.add(customer);
        }
        return customers;
    }
}
