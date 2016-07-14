package com.bawi.spring.boot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class MyComplexCF {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyComplexCF.class);

    public static void main(String[] args) {

        List<String> fistNames = Arrays.asList("a", "bb", "ccc", "dddd");
        ExecutorService executorService = Executors.newFixedThreadPool(fistNames.size());

        List<CompletableFuture<Double>> doublesCF = fistNames
            .stream()
            .map(s -> CompletableFuture.supplyAsync(() -> {
                        sleepSeconds(1);
                        LOGGER.info("Finished sleeping in supplyAsync");
                        return s.length();
                    }, executorService)
                    .thenApplyAsync (i -> {
                        sleepSeconds(1);
                        LOGGER.info("Finished sleeping in thenApply");
                        return new Double(i);
                    }, executorService
                )
            )
            .collect(Collectors.toList());

        CompletableFuture<Void> voidCF = CompletableFuture.allOf(doublesCF.toArray(new CompletableFuture[doublesCF.size()]));
        CompletableFuture<List<Double>> listOfDoublesCF = voidCF.thenApply(v -> doublesCF
            .stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList()));

        CompletableFuture<List<Double>> timeoutCF = new CompletableFuture<>();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, r -> {
            ThreadFactory threadFactory = Executors.defaultThreadFactory();
            Thread thread = threadFactory.newThread(r);
            thread.setDaemon(true);
            return thread;
        });
        scheduledExecutorService.schedule(() -> {
            LOGGER.info("Completing exceptionally");
            return timeoutCF.completeExceptionally(new RuntimeException("Timeout"));
        }, 3, TimeUnit.SECONDS);


        CompletableFuture<Integer> firstCF = listOfDoublesCF.applyToEither(timeoutCF, List::size);
        try {
            Integer value = firstCF.get();
            System.out.println("value=" + value);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

    }

    private static void sleepSeconds(int sleepSeconds) {
        try {
            TimeUnit.SECONDS.sleep(sleepSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
