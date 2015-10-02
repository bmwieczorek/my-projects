package com.bawi.threads;
import java.util.HashMap;
import java.util.Map;

public class NotThreadSafeApplication {
    
    private static final int ITERATIONS = 1000;
    static Map<Integer, Boolean> map = new HashMap<>();
    
    static class MyThread extends Thread {
        
        private final int ticketNumber;

        public MyThread(int ticketNumber) {
            this.ticketNumber = ticketNumber;
        }
        
        @Override
        public void run() {
//            synchronized (NotThreadSafeApplication.class) {
                findAndBuyIfFound();
//            }
        }

        private void findAndBuyIfFound() {
            boolean available = map.get(ticketNumber);
            if (available) {
                map.put(ticketNumber, false);
                System.out.println(Thread.currentThread() + " bought: " + ticketNumber);
            } else {
                System.out.println(Thread.currentThread() + " not available: " + ticketNumber);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= ITERATIONS; i++) {
            map.put(i, true);
        }
        for (int i = 1; i <= ITERATIONS; i++) {
            new MyThread(i).start();
            new MyThread(i).start();
            sleepMillis(10);
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
//Thread[Thread-487,5,main] bought: 244
//Thread[Thread-486,5,main] bought: 244
