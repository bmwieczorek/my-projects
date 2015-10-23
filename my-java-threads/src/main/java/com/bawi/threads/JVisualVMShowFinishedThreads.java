package com.bawi.threads;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JVisualVMShowFinishedThreads {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JVisualVMShowFinishedThreads.class);  
    public static void main(String[] args) throws InterruptedException {
        final List<Integer> sleepSecondsList = Arrays.asList(15, 15);
        
        new Thread(createTask("t0", sleepSecondsList)).start();
        new Thread(createTask("t1", sleepSecondsList)).start();
        new Thread(createTask("t2", sleepSecondsList)).start();

        sleepSeconds("main", 10);

        new Thread(createTask("t3", sleepSecondsList)).start();
        new Thread(createTask("t4", sleepSecondsList)).start();
        new Thread(createTask("t5", sleepSecondsList)).start();
        
        sleepSeconds("main", 100);
    }

    private static Runnable createTask(String id, final List<Integer> sleepSecondsList) {
        return () -> {
            sleepSecondsList
                .parallelStream()
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
