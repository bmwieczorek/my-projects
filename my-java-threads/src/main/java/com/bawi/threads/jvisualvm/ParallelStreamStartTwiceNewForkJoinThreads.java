package com.bawi.threads.jvisualvm;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParallelStreamStartTwiceNewForkJoinThreads {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParallelStreamStartTwiceNewForkJoinThreads.class);

    /*
    This time after first 3 threads (t0-t2) finished then main thread waited much longer (here 13 seconds) 
    and then started new 3 threads (t3-t5). 13 seconds was apparently long enough to stop all the 3 fork-join pool 
    workers and new forkjoin workers needed to be created to handle parallel processing.
    */
    public static void main(String[] args) throws InterruptedException {
      LOGGER.debug(currentThreadId() + "Available cores: {}", Runtime.getRuntime().availableProcessors()); // n=4 CPU cores 
      // on my notebook

      // In general CPU core count = ForkJoin pool size that consists of 1 current and n-1 worker threads

      List<Integer> sleepSecondsList = Arrays.asList(5, 5);// assuming all 3 workers reuse then lets start 
      // 6 tasks for 3 manually started threads + 3 workers what gives 2 tasks (list size = 2) per thread 

        sleepSeconds("main", 15);

        Thread t0 = new Thread(createTask("t0", sleepSecondsList));
        Thread t1 = new Thread(createTask("t1", sleepSecondsList));
        Thread t2 = new Thread(createTask("t2", sleepSecondsList));
        t0.start();
        t1.start();
        t2.start();

        t0.join();
        t1.join();
        t2.join();
        LOGGER.debug(currentThreadId() + "Threads t0, t1, t2 finished");

        sleepSeconds("main", 13); // 13 seconds was apparently enough to stop all the 3 fork-join workers 
                                  // and new fork-join workers needed to be created to handle parallel processing

        Thread t3 = new Thread(createTask("t3", sleepSecondsList));
        Thread t4 = new Thread(createTask("t4", sleepSecondsList));
        Thread t5 = new Thread(createTask("t5", sleepSecondsList));
        t3.start();
        t4.start();
        t5.start();

        t3.join();
        t4.join();
        t5.join();

        LOGGER.debug(currentThreadId() + "Threads t3, t4, t5 finished");
        sleepSeconds("main", 100);

        // Java started initially with 9 daemon threads and one main thread from the application. 
        // Then 6 threads were programmatically started and twice 3 worker fork join threads from 
        // parallel stream. That makes 22 threads started in total. See screenshot:
         
    }

    private static Runnable createTask(String id, final List<Integer> sleepSecondsList) {
        return () -> {
            sleepSecondsList
                .parallelStream()
                .forEach(t -> sleepSeconds(id, t));
        };
    }

    private static void sleepSeconds(String id, int sleepSeconds) {
        LOGGER.debug(currentThreadId() + "{} about to sleep {}", id, sleepSeconds);
        try{
            Thread.sleep(1000 * sleepSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String currentThreadId() {
        return String.format("Thread id=%-2s|", Thread.currentThread().getId());
    }
}
/*
2015-11-02 21:15:36,527|main                            |Thread id=1 |Available cores: 4
2015-11-02 21:15:36,528|main                            |Thread id=1 |main about to sleep 15

2015-11-02 21:15:51,598|Thread-1                        |Thread id=11|t1 about to sleep 5
2015-11-02 21:15:51,598|ForkJoinPool.commonPool-worker-2|Thread id=15|t0 about to sleep 5
2015-11-02 21:15:51,598|ForkJoinPool.commonPool-worker-3|Thread id=13|t2 about to sleep 5
2015-11-02 21:15:51,598|Thread-0                        |Thread id=10|t0 about to sleep 5
2015-11-02 21:15:51,598|Thread-2                        |Thread id=12|t2 about to sleep 5
2015-11-02 21:15:51,598|ForkJoinPool.commonPool-worker-1|Thread id=14|t1 about to sleep 5

2015-11-02 21:15:56,600|main                            |Thread id=1 |Threads t0, t1, t2 finished
2015-11-02 21:15:56,602|main                            |Thread id=1 |main about to sleep 13

2015-11-02 21:16:09,605|Thread-4                        |Thread id=17|t4 about to sleep 5
2015-11-02 21:16:09,605|Thread-3                        |Thread id=16|t3 about to sleep 5
2015-11-02 21:16:09,606|Thread-5                        |Thread id=18|t5 about to sleep 5
2015-11-02 21:16:09,606|ForkJoinPool.commonPool-worker-0|Thread id=19|t4 about to sleep 5
2015-11-02 21:16:09,606|ForkJoinPool.commonPool-worker-2|Thread id=21|t3 about to sleep 5
2015-11-02 21:16:09,607|ForkJoinPool.commonPool-worker-1|Thread id=20|t5 about to sleep 5

2015-11-02 21:16:14,611|main                            |Thread id=1 |Threads t3, t4, t5 finished
2015-11-02 21:16:14,612|main                            |Thread id=1 |main about to sleep 100


*/
