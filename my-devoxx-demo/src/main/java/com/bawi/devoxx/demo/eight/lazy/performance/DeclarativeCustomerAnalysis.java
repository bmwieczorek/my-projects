package com.bawi.devoxx.demo.eight.lazy.performance;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class DeclarativeCustomerAnalysis {
    static LongRunningAccountBalanceRetriever longRunningAccountBalanceRetriever = new LongRunningAccountBalanceRetriever();

    public static void main(String[] args) {
        List<String> names = asList("Bob", "Bill", "Joe", "Mike", "Kim", "Lucy");
        long start = System.currentTimeMillis();

        // 1. stream single usage,
        // laziness gain: duration 2s always (steam()) or 1-2s (parallelStream()) in getCustomers
        Stream<Customer> customers = getCustomers(names);
        printFirstMillionaire(customers);

        // 2a. persist stream as collection since cannot reuse the closed stream for the second operation,
        // no laziness gain,
        // gain with parallelStream() 2s vs stream() 6s in getCustomers
        // Stream<Customer> customers = getCustomers(names); // just extraction, no terminal operation called
        // System.out.println("Stream extracted to local variable");
        // List<Customer> customerList 
        //    = customers
        //   // .parallel() // parallelize if element evaluation is not CPU intensive (OI or sleep operation)  
        //    .collect(Collectors.toList()); // toList is a terminal operation all elements need to be evaluated  
        // System.out.println("Stream persisted to the list");
        // printFirstMillionaire(customerList.stream());
        // printFirstCustomer(customerList.stream());

        // 3a. every time create new stream since cannot reuse the closed stream for the second operation,
        // laziness gain
        // parallelStream() 3s vs stream() 3s in getCustomers
        // printFirstMillionaire(getCustomers(names)); // (duration 1-6s, usually 1-2s) filter first matching, do not evaluate others
        // printFirstCustomer(getCustomers(names));    // (duration 1s) gain as only 1 element to evaluate when building a stream

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
                .stream()
                //.parallelStream() // customers in parallel, number of investigations no guaranteed 
                .map(name -> {
                        int customerId = id.getAndIncrement();
                        Customer customer = new Customer(name, customerId, longRunningAccountBalanceRetriever.getAccountBallance(customerId));
                        System.out.println("getCustomers: " + customer + ", " + Thread.currentThread());
                        return customer;
                    });
        //@formatter:on
    }
}
