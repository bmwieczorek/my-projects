package com.bawi.spring.boot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class TestParallel {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestParallel.class);

    public static void main(String[] args) {
        LOGGER.info("Started");
        long startMillis = System.currentTimeMillis();

//        List<Integer> results = threads(); // 12s

//        List<Integer> results = mapSequentiallyWithOnlyMainThread(); // 12s

//        List<Integer> results = mapParallel(); // 4s

//        List<Integer> results = executorServiceGet(); // 6s

//        List<Integer> results = executionCompletionServiceTakeAndGet(); // 4s

//        List<Integer> results = completableFuture(); //4s

        List<Integer> results = completableFutureAsyncExecutor(); //4s

        long stopMillis = System.currentTimeMillis();
        LOGGER.info("Finished in {} seconds, results={}", (stopMillis - startMillis) / 1000, results);
    }

    private static List<Integer> threads() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        return numbers
            .stream()
            .map(n -> startThreadAndJoin(() -> download(n, numbers.size() - n + 1)))
            .map(n -> startThreadAndJoin(() -> parse(n, n)))
            .collect(Collectors.toList());
    }

    private static Integer startThreadAndJoin(Supplier<Integer> supplier) {
        AtomicInteger value = new AtomicInteger();
        Thread thread = new Thread(() -> {
            value.set(supplier.get());
        });
        thread.start();
        try {
            thread.join();
            return value.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Integer> executionCompletionServiceTakeAndGet() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        ExecutorService executorService = Executors.newFixedThreadPool(numbers.size());
        ExecutorCompletionService<Integer> executorCompletionService = new ExecutorCompletionService<>(executorService);

        numbers
            .stream()
            .forEach(n -> executorCompletionService.submit(() -> download(n, numbers.size() - n + 1)));

        numbers
            .stream()
            .forEach(ignored -> {
                try {
                    Future<Integer> future = executorCompletionService.take();
                    Integer value = future.get();
                    executorCompletionService.submit(() -> parse(value, value));
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });

        return numbers
            .stream()
            .map(ignore -> {
                try {
                    Future<Integer> future = executorCompletionService.take();
                    return future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            })
            .collect(Collectors.toList());
    }

    private static List<Integer> executorServiceGet() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        ExecutorService executorService = Executors.newFixedThreadPool(numbers.size());
        List<Future<Integer>> downloadedFutures = numbers
            .stream()
            .map(n -> {
                Callable<Integer> callable = () -> download(n, numbers.size() - n + 1);
                return executorService.submit(callable);
            })
            .collect(Collectors.toList());

        List<Future<Integer>> parsedFutures = numbers
            .stream()
            .map(n -> {
                Future<Integer> future = downloadedFutures.get(n - 1);
                try {
                    Integer value = future.get();
                    return executorService.submit(() -> parse(value, n));
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            })
            .collect(Collectors.toList());

        return parsedFutures
            .stream()
            .map(future -> {
                try {
                    return future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            })
            .collect(Collectors.toList());
    }

    private static List<Integer> mapParallel() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        return numbers
            .parallelStream()
            .map(n -> download(n, numbers.size() - n + 1))
            .map(n -> parse(n, n))
            .collect(Collectors.toList());
    }

    private static List<Integer> mapSequentiallyWithOnlyMainThread() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        return numbers
            .stream()
            .map(n -> download(n, numbers.size() - n + 1))
            .map(n -> parse(n, n))
            .collect(Collectors.toList());
    }

    private static List<Integer> completableFuture() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        List<CompletableFuture<Integer>> cfs = numbers
            .stream()
            .map(n ->
                CompletableFuture.supplyAsync(() -> download(n, numbers.size() - n + 1))
                    .thenApply(id -> parse(id, n)))
            .collect(Collectors.toList());

        CompletableFuture<List<Integer>> allResultsCF = CompletableFuture.allOf(cfs.toArray(new CompletableFuture[numbers.size()]))
            .thenApply(v -> cfs
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList())
            );

        return allResultsCF.join();
    }

    static int download(int id, int seconds) {
        LOGGER.info("Downloading {} and sleeping {} second(s)", id, seconds);
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Downloaded {}", id);
        return id;
    }

    static int parse(int id, int seconds) {
        LOGGER.info("Parsing {} and sleeping {} second(s)", id, seconds);
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Parsed {}", id);
        return id;
    }

    private static List<Integer> completableFutureAsyncExecutor() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        ExecutorService executorService = Executors.newFixedThreadPool(numbers.size());
        List<CompletableFuture<Integer>> cfs = numbers
            .stream()
            .map(n ->
                CompletableFuture.supplyAsync(() -> download(n, numbers.size() - n + 1), executorService)
                    .thenApplyAsync(id -> parse(id, n), executorService)
            )
            .collect(Collectors.toList());

        CompletableFuture<List<Integer>> allResultsCF = CompletableFuture.allOf(cfs.toArray(new CompletableFuture[numbers.size()]))
            .thenApply(v -> cfs
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList())
            );

        return allResultsCF.join();
    }
}
