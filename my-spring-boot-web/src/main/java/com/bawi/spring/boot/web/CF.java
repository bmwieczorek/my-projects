package com.bawi.spring.boot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by Bartosz Wieczorek on 6/25/2016.
 */
public class CF {

    private static final Logger LOGGER = LoggerFactory.getLogger(CF.class);

    public static void main(String[] args) {

        CompletableFuture<String> bobCF = new CompletableFuture<>();
        bobCF.complete("a");


        List<CompletableFuture<String>> numbersCF = Arrays.asList(3, 2, 1, 0)
            .stream()
            .map(i -> CompletableFuture.supplyAsync(() -> 12 / i)
                                        .exceptionally(throwable -> {
                                            LOGGER.error(throwable.getMessage());
                                            return 0;
                                        })
                                        .whenComplete((n, e) -> {
                                            System.out.println(n);
                                            try {
                                                TimeUnit.SECONDS.sleep(2);
                                            } catch (InterruptedException e1) {
                                                LOGGER.error("Exception while sleeping", e1);
                                            }
                                        })
                                        .thenApply(n -> n + "" + n))
            .collect(Collectors.toList());

        CompletableFuture<Void> voidCF = CompletableFuture.allOf(numbersCF.toArray(new CompletableFuture[numbersCF.size()]));
        CompletableFuture<List<String>> aggregatedNumbersCF = voidCF.thenApply(v -> numbersCF
            .stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList())
        );

        CompletableFuture<List<String>> combinedNumbers = aggregatedNumbersCF
                                                            .thenCombine(bobCF, (list, name) -> list
                                                                .stream()
                                                                .map(e -> e + name)
                                                                .collect(Collectors.toList()));

        CompletableFuture<Integer> sizeCF = combinedNumbers
            .thenCompose(list -> CompletableFuture.supplyAsync(() -> list.size()));

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                ThreadFactory threadFactory = Executors.defaultThreadFactory();
                Thread thread = threadFactory.newThread(r);
                thread.setDaemon(true);
                return thread;
            }
        });

        CompletableFuture<Integer> timeoutCF = new CompletableFuture<>();
        scheduledExecutorService.schedule(() -> timeoutCF.completeExceptionally(new RuntimeException("Timeout")), 1, TimeUnit.SECONDS);

        CompletableFuture<Integer> uCompletableFuture = sizeCF.applyToEither(timeoutCF, i -> 2 * i);
        CompletableFuture<Integer> handle = uCompletableFuture.handle((ok, ex) -> {
            if (ex != null) {
                LOGGER.error("Error processing", ex);
                ex = null;
                return -1;
            }
            return ok;
        });
        CompletableFuture<Integer> integerCompletableFuture = handle.whenComplete((i, e) -> LOGGER.info("i={}, ex={}", i, e));

        try {
            System.out.println("result=" + integerCompletableFuture.get(10, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            LOGGER.error("Problem in processing", e);
        }

    }
}
