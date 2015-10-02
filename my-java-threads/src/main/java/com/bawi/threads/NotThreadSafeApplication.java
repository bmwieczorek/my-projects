package com.bawi.threads;

import java.util.HashMap;
import java.util.Map;

public class NotThreadSafeApplication {

    private static final int ITERATIONS = 1000;
    private static Map<Integer, Boolean> map = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 1; i <= ITERATIONS; i++) {
            map.put(i, true);
        }
        for (int i = 1; i <= ITERATIONS; i++) {
            new Thread(() -> findAndBuyIfFound(1)).start();
            new Thread(() -> findAndBuyIfFound(1)).start();
            sleepMillis(10);
        }
    }

    private static void findAndBuyIfFound(int ticketNumber) {
        synchronized (NotThreadSafeApplication.class) {
            boolean available = map.get(ticketNumber);
            if (available) {
                map.put(ticketNumber, false);
                System.out.println(Thread.currentThread() + " bought: " + ticketNumber);
            } else {
                System.out.println(Thread.currentThread() + " not available: " + ticketNumber);
            }
        }
    }

    public static void sleepMillis(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// result:
// If not synchronized in line 20, then two thread bought the same ticket
// Thread[Thread-487,5,main] bought: 244
// Thread[Thread-486,5,main] bought: 244
