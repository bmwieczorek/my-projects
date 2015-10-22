package com.bawi.threads.race.condition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotThreadSafeApplication {

    private static final int ITERATIONS = 1000;
    private static Map<Integer, Boolean> map = new ConcurrentHashMap<>();
    private static final String BOUGHT = "bought";
    private static final Path PATH;
    private static final Logger LOGGER;
    static {
        PATH = Paths.get("target/file.log");
        try {
            Files.deleteIfExists(PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER = LoggerFactory.getLogger(NotThreadSafeApplication.class);
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 1; i <= ITERATIONS; i++) {
            map.put(i, true);
        }
        for (int i = 1; i <= ITERATIONS; i++) {
            final int j = i;
            Thread t1 = new Thread(() -> findAndBuyIfFound(j));
            Thread t2 = new Thread(() -> findAndBuyIfFound(j));
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        }

        Set<String> boughtTicketNumber = new HashSet<>();
        Files.lines(PATH)
            .filter(line -> line.contains(BOUGHT))
           .forEach(line -> { 
                String[] split = line.split(":");
                String ticket = split[2];
                System.out.println(line);
                if (!boughtTicketNumber.add(ticket)) { // adds return false for duplication
                    throw new RuntimeException("ticket booked twice: " + ticket);
                }
            }
        );
    }

    private static void findAndBuyIfFound(int ticketNumber) {
        //synchronized (NotThreadSafeApplication.class) {
            boolean available = map.get(ticketNumber);
            if (available) {
                map.put(ticketNumber, false);
                LOGGER.info("bought:{}", ticketNumber);
            } else {
                LOGGER.info("not available:{}", ticketNumber);
            }
       //}
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
// HashMap
// Thread[Thread-487,5,main] bought: 244
// Thread[Thread-486,5,main] bought: 244

// ConcurrentHashMap
// Thread[Thread-1095,5,main] bought: 548
// Thread[Thread-1094,5,main] bought: 548
