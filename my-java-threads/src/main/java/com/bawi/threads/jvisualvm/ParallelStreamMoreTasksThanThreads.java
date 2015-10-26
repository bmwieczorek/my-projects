package com.bawi.threads.jvisualvm;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParallelStreamMoreTasksThanThreads {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParallelStreamMoreTasksThanThreads.class);

    public static void main(String[] args) throws InterruptedException {
      LOGGER.debug("Available cores: {}",Runtime.getRuntime().availableProcessors());// n=4 CPU cores 
                                                                                    // on my notebook

    //In general CPU core count = ForkJoin pool size that consists of 1 current and n-1 worker threads

      List<Integer> sleepSecondsList  
                = Arrays.asList(3, 3, 3, 3, 3); // each parallel task will sleep 3 seconds
                            // set list size to CPU core count + 1 to have more tasks than threads

        sleepSeconds("main", 15);

        Thread t0 = new Thread(createTask("t0", sleepSecondsList));
        t0.start();

        t0.join();
        LOGGER.debug("Thread t0 finished");

        sleepSeconds("main", 100);
    }

    private static Runnable createTask(String id, final List<Integer> sleepSecondsList) {
        return () -> {
            sleepSecondsList
                .parallelStream()

                //.stream() // note: switching from parallel stream to stream would increase processing 
                            // time from 2x3s to 5x3s as each task would be executed sequentially by the
                            // t0 thread. On the other hand no fork join workers would be started.

                .forEach(t -> sleepSeconds(id, t));
        };
    }

    private static void sleepSeconds(String id, int sleepSeconds) {
        LOGGER.debug("{} about to sleep {}", id, sleepSeconds);
        try{
            Thread.sleep(1000 * sleepSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Note: Java initially started with 10 threads (including the main thread). 
// The main thread manually new t0 thread to process 5 tasks using parallelStream(). 
// Internally parallelStream() started Fork-Join thread pool with 3 workers. 
// As a result of that 4 additional threads were started apart from the main thread.

/*
Output:
2015-10-25 10:47:51,073|main      |Available cores: 4
2015-10-25 10:47:51,074|main      |main about to sleep 15

2015-10-25 10:48:06,143|Thread-0  |t0 about to sleep 3
2015-10-25 10:48:06,143|ForkJoinPool.commonPool-worker-3|t0 about to sleep 3
2015-10-25 10:48:06,143|ForkJoinPool.commonPool-worker-1|t0 about to sleep 3
2015-10-25 10:48:06,143|ForkJoinPool.commonPool-worker-2|t0 about to sleep 3

2015-10-25 10:48:09,143|ForkJoinPool.commonPool-worker-1|t0 about to sleep 3 <- completes work in next 3 seconds "slot"

2015-10-25 10:48:12,144|main      |Thread t0 finished
2015-10-25 10:48:12,144|main      |main about to sleep 100
*/