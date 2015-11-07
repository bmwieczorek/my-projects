package com.bawi.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFixedThreadPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyFixedThreadPool.class);
    public static void main(String[] args) {
        sleepSeconds(10); // give 10 seconds to connect to JVisualMV

        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
        newFixedThreadPool.submit(() -> sleepSeconds(5)); // starts only first thread
        sleepSeconds(6); // wait 6s so first thread finishes running the task
        newFixedThreadPool.submit(() -> sleepSeconds(5)); // starts 2nd thread for 2nd task even
                                                          // though the 1st thread finished 1st task and is waiting
        sleepSeconds(1); // wait 1s to add 3rd task
        newFixedThreadPool.submit(() -> sleepSeconds(5)); // 3rd task is picked by 1st thread that was waiting
        sleepSeconds(1); // wait 1s to add 4rd task
        newFixedThreadPool.submit(() -> sleepSeconds(5)); // need to wait 3s for available thread before processing 4rd task 
        sleepSeconds(80);
    }

    private static void sleepSeconds(int seconds) {
        try {
            LOGGER.debug(currentThreadId() + "About to sleep {} seconds", seconds);
            TimeUnit.SECONDS.sleep(seconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String currentThreadId() {
        long id = Thread.currentThread().getId();
        return String.format("Thread id=%-2s|", id);
    }
}
/*
Output:
2015-11-05 18:05:04,289|main |Thread id=1 |About to sleep 10 seconds
2015-11-05 18:05:14,296|main |Thread id=1 |About to sleep 6 seconds
2015-11-05 18:05:14,296|pool-1-thread-1 |Thread id=16|About to sleep 5 seconds
2015-11-05 18:05:20,296|main |Thread id=1 |About to sleep 1 seconds
2015-11-05 18:05:20,296|pool-1-thread-2 |Thread id=17|About to sleep 5 seconds
2015-11-05 18:05:21,311|main |Thread id=1 |About to sleep 1 seconds
2015-11-05 18:05:21,311|pool-1-thread-1 |Thread id=16|About to sleep 5 seconds
2015-11-05 18:05:22,315|main |Thread id=1 |About to sleep 80 seconds
2015-11-05 18:05:25,296|pool-1-thread-2 |Thread id=17|About to sleep 5 seconds

Note that only two threads (pool-1-thread-1,id=16 and pool-1-thread-2, id=17) were involved in running 4 tasks. 
Notice on the JVisualVM that the pool did not shrink to 1 or 0 and both of the pool threads remained 
available/waiting (parked) for more that 60 seconds and even after main thread finished. 
Main thread finished in 80 seconds after submitting last task.

public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(
               nThreads, // once created a thread do not shrink the pool 
               nThreads,
               0L, TimeUnit.MILLISECONDS, // idle threads waits forever for new tasks
               new LinkedBlockingQueue<Runnable>());
}
*/
