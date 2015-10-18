package com.bawi.threads;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ClosingExecutorServiceTest {
    public static void main(String[] args) {
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> future = newSingleThreadScheduledExecutor.scheduleWithFixedDelay(() -> System.out.println("Running"), 5, 1, TimeUnit.SECONDS);
        future.cancel(true);
        newSingleThreadScheduledExecutor.shutdown(); // need to close executor (pool) to shutdown threads and stop the jvm
    }

}
