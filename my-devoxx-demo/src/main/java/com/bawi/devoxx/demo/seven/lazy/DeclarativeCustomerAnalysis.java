package com.bawi.devoxx.demo.seven.lazy;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class DeclarativeCustomerAnalysis {
    static AccountBalanceRetriever accountBalanceRetriever = new AccountBalanceRetriever();

    public static void main(String[] args) {
        List<String> names = asList("Bob", "Bill", "Joe", "Mike", "Kim", "Lucy");
        long start = System.currentTimeMillis();

        // 1. stream single usage,
        // laziness gain: duration 2s (steam()) or 1s (parallelStream()) in getCustomers
        Stream<Customer> customers = getCustomers(names);
        printFirstMillionaire(customers);

        // 2a. persist stream as collection since cannot reuse the closed stream for the second operation,
        // no laziness gain,
        // gain with parallelStream() 2s vs stream() 6s in getCustomers
        // Stream<Customer> customers = getCustomers(names);
        // List<Customer> customerList = customers.collect(Collectors.toList());
        // printFirstMillionaire(customerList.stream());
        // printFirstCustomer(customerList.stream());

        // 3a. every time create new stream since cannot reuse the closed stream for the second operation,
        // laziness gain
        // parallelStream() 3s vs stream() 3s in getCustomers
        // printFirstMillionaire(getCustomers(names));
        // printFirstCustomer(getCustomers(names));

        long stop = System.currentTimeMillis();
        System.out.println("Took ms: " + (stop - start));
    }

    private static void printFirstCustomer(Stream<Customer> customers) {
        System.out.println("First customer: " + customers.findFirst().get());
    }

    private static void printFirstMillionaire(Stream<Customer> customers) {
        //@formatter:off
        System.out.println("We have at least millionaire: " + 
                customers
                    .filter(customer -> customer.getBalance() >= Double.valueOf(1000000))
                    .findFirst()
                    .get());
        //@formatter:on
    }

    private static Stream<Customer> getCustomers(List<String> names) {
        AtomicInteger id = new AtomicInteger();
        //@formatter:off
        return names
                //.stream()
                .parallelStream()
                .map(name -> {
                        int customerId = id.getAndIncrement();
                        return new Customer(name, customerId, accountBalanceRetriever.getAccountBallance(customerId));
                    });
        //@formatter:on
    }
}
