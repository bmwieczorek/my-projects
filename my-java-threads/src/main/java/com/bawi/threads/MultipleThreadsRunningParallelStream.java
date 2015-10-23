package com.bawi.threads;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultipleThreadsRunningParallelStream {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultipleThreadsRunningParallelStream.class);  

    public static void main(String[] args) throws InterruptedException {

        LOGGER.debug("Availble cores: {}", Runtime.getRuntime().availableProcessors());

        List<Integer> sleepSecondsList = Arrays.asList(3, 3, 3);

        new Thread(createTask("t0", sleepSecondsList)).start();
        new Thread(createTask("t1", sleepSecondsList)).start();
        new Thread(createTask("t2", sleepSecondsList)).start();
        new Thread(createTask("t3", sleepSecondsList)).start();

        sleepSeconds("main", 10);
    }

    private static Runnable createTask(String id, final List<Integer> sleepSecondsList) {
        return () -> {
            sleepSecondsList
                .parallelStream() // uses ForkJoinPool of size 4 (as 4 cores) with 
                                  // one current threads and 3 shared ForkJoinPool.commonPool-workers  
                .forEach(t -> sleepSeconds(id, t));
        };
    }

    private static void sleepSeconds(String id, int sleepSeconds) {
        LOGGER.debug("{} about to sleep {}", id, sleepSeconds);
        try {
            Thread.sleep(1000 * sleepSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
