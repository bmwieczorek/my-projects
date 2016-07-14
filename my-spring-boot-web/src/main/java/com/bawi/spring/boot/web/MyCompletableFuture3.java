package com.bawi.spring.boot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MyCompletableFuture3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyCompletableFuture3.class);

    public static void main(String[] args) throws InterruptedException {
        LOGGER.info("started");

        List<CompletableFuture<Double>> completableFutures = Arrays.asList("a", "ab", "abc", "abcd")
            .stream()
            .map(text ->  CompletableFuture.supplyAsync(() -> produceText(text)))
            .map(stringCF -> stringCF.thenApply(MyCompletableFuture3::calcStringLength))
            .map(integerCF -> integerCF.thenCompose(length -> CompletableFuture.supplyAsync(() -> calcCirclePerimeter(length))))
            .collect(Collectors.toList());

        CompletableFuture<List<Double>> allResultsCF = allOfAggregatedFuture(completableFutures);

        LOGGER.info("created completable future, waiting for completable future to finish:");
        List<Double> results = allResultsCF.join();
        LOGGER.info("Results: {}", results);
        LOGGER.info("finished");
   }

    private static <T> CompletableFuture<List<T>> allOfAggregatedFuture(List<CompletableFuture<T>> completableFutures) {
        CompletableFuture[] cfs = completableFutures.toArray(new CompletableFuture[completableFutures.size()]);
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(cfs);
        return voidCompletableFuture.thenApply(v -> completableFutures
            .stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList()));
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

    private static void sleepMillis(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
