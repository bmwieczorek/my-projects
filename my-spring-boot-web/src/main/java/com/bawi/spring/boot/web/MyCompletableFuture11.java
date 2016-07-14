package com.bawi.spring.boot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class MyCompletableFuture11 {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyCompletableFuture11.class);

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": started");
        Arrays.asList("a", "ab", "abc", "abcd")
            .stream()
            .map(text -> {
                CompletableFuture<String> stringCF = CompletableFuture.supplyAsync(() -> produceText(text));
                CompletableFuture<Integer> integerCF = stringCF.thenApplyAsync(MyCompletableFuture11::calcStringLength);
                CompletableFuture<Double> doubleCF = integerCF.thenApply(MyCompletableFuture11::calcCirclePerimeter);
                return doubleCF;
                }
            );


        System.out.println(Thread.currentThread().getName() + ": created completable future, about to sleep");
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + ": finished");
   }

    static String produceText(String text)  {
        LOGGER.info("[daemon={}] 1: Producing text: {}", Thread.currentThread().isDaemon(), text);
        sleepMillis(1000);
        return text;
    }

    static int calcStringLength(String text) {
        int length = text.length();
        LOGGER.info("[daemon={}] 2: Calculating string length: {}", Thread.currentThread().isDaemon(), length);
        sleepMillis(1000);
        return length;
    }

    static double calcCirclePerimeter(int r) {
        double perimeter = 2 * Math.PI * r;
        LOGGER.info("[daemon={}] 3: Calculating circle perimeter: {}", Thread.currentThread().isDaemon(), perimeter);
        sleepMillis(1000);
        return perimeter;
    }

    static void print(Double d) {
        LOGGER.info("[daemon={}] 4: Printing: {}", Thread.currentThread().isDaemon(), d);
        System.out.println("d=" + d);
    }

    private static void sleepMillis(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
