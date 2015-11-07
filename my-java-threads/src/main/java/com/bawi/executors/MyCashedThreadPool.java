package com.bawi.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCashedThreadPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyCashedThreadPool.class);

    public static void main(String[] args) {
        sleepSeconds(10); // give 10 seconds to connect to JVisualMV

        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        newCachedThreadPool.submit(() -> sleepSeconds(5)); // starts only first thread
        sleepSeconds(6); // wait 6s so first thread finishes running the task
        newCachedThreadPool.submit(() -> sleepSeconds(5)); // reuses 1nd thread as available/waiting for 2nd task
        sleepSeconds(1); // wait 1s to add 3rd task
        newCachedThreadPool.submit(() -> sleepSeconds(5)); // starts 2nd thread for 3rd task as 1st thread still busy
        sleepSeconds(1); // wait 1s to add 4rd task
        newCachedThreadPool.submit(() -> sleepSeconds(5)); // starts 3rd thread for 4rd task as 1st and 2nd thread still busy 
        sleepSeconds(70); // wait 70s to so 1st, 2nd and 3rd thread terminate after 60s of being idle 
        newCachedThreadPool.submit(() -> sleepSeconds(5)); // starts 4th thread for 5th task (as no previous threads were available)   
        sleepSeconds(10);
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
2015-11-05 21:40:19,675|main |Thread id=1 |About to sleep 10 seconds
2015-11-05 21:40:29,688|main |Thread id=1 |About to sleep 6 seconds
2015-11-05 21:40:29,688|pool-1-thread-1 |Thread id=16|About to sleep 5 seconds
2015-11-05 21:40:35,701|main |Thread id=1 |About to sleep 1 seconds
2015-11-05 21:40:35,701|pool-1-thread-1 |Thread id=16|About to sleep 5 seconds
2015-11-05 21:40:36,702|main |Thread id=1 |About to sleep 1 seconds
2015-11-05 21:40:36,702|pool-1-thread-2 |Thread id=17|About to sleep 5 seconds
2015-11-05 21:40:37,705|main |Thread id=1 |About to sleep 70 seconds
2015-11-05 21:40:37,705|pool-1-thread-3 |Thread id=18|About to sleep 5 seconds
2015-11-05 21:41:47,710|main |Thread id=1 |About to sleep 10 seconds
2015-11-05 21:41:47,710|pool-1-thread-4 |Thread id=19|About to sleep 5 seconds

Note that min or max threads in the pool is not defined assuming 0 and Integer.MAX_VALUE. 
When a thread is available then it will be reused for a task. If thread is not available a new thread 
will be created in order to process a task. If a thread remains waiting (idle) for 60 seconds 
then it will be terminated. That could be seen on the JVisualVM where the thread pool shrinks to 0 
at a certain moment and later expands again on new task.

public static ExecutorService newCachedThreadPool() {
    return new ThreadPoolExecutor(0, // try to reduce the pool size to 0 if threads are idle long enough
                            Integer.MAX_VALUE, // almost no limit for the number of new threads started
                            60L, TimeUnit.SECONDS, // terminate thread after 60s idle
                            new SynchronousQueue<Runnable>());
}
*/