package com.bawi.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotThreadSafeApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotThreadSafeApplication.class);
    private static final int ITERATIONS = 1000;
    private static Map<Integer, Boolean> map = new ConcurrentHashMap<>();
    private static final String BOUGHT = "bought";

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("target/file.log");
        // Files.deleteIfExists(path);
        
        for (int i = 1; i <= ITERATIONS; i++) {
            map.put(i, true);
        }
        for (int i = 1; i <= ITERATIONS; i++) {
            final int j = i;
            new Thread(() -> findAndBuyIfFound(j)).start();
            new Thread(() -> findAndBuyIfFound(j)).start();
            sleepMillis(10);
        }

        Map<String, Integer> ticketToBookingCountMapping = new HashMap<>();
        Files.lines(path)
            .forEach(line -> { 
                String[] split = line.split(":");
                String ticket = split[2];
                System.out.println(line);
                ticketToBookingCountMapping.compute(ticket, (k, v) -> {
                    if (!BOUGHT.equals(split[1])) {
                        return null;
                    }
                    if (v == null) {
                        return 1;
                    }
                    throw new RuntimeException("ticket booked twice: " + ticket);
                });
            }
        );
    }

    private static void findAndBuyIfFound(int ticketNumber) {
        synchronized (NotThreadSafeApplication.class) {
            boolean available = map.get(ticketNumber);
            if (available) {
                map.put(ticketNumber, false);
                LOGGER.info("bought:{}", ticketNumber);
            } else {
                LOGGER.info("not available:{}", ticketNumber);
            }
       }
    }

    public static void sleepMillis(int i) {
        new Date().compareTo(new Date());
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// result:
// If not synchronized in line 20, then two thread bought the same ticket
// HashMap
// Thread[Thread-487,5,main] bought: 244
// Thread[Thread-486,5,main] bought: 244

// ConcurrentHashMap
// Thread[Thread-1095,5,main] bought: 548
// Thread[Thread-1094,5,main] bought: 548
