package com.bawi.countdownlatch;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestThreadsTalkingToEachOtherProducingResult {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestThreadsTalkingToEachOtherProducingResult.class);

    public static class Holder {
        private String backendRequest;
        private CountDownLatch latch;

        public String getBackendRequest() {
            return backendRequest;
        }

        public CountDownLatch getLatch() {
            return latch;
        }

        public void setLatch(CountDownLatch latch) {
            this.latch = latch;
        }

        public void setBackendRequest(String backendRequest) {
            this.backendRequest = backendRequest;
        }

        @Override
        public String toString() {
            return "Holder [backendRequest=" + backendRequest + ", latch=" + latch + "]";
        }

    }

    private static class FrontendRequestTask implements Callable<String> {

        private final String frontendRequest;
        private final String key;
        private final Map<String, Holder> map;

        public FrontendRequestTask(String data, Map<String, Holder> map, String key) {
            this.frontendRequest = data;
            this.map = map;
            this.key = key;
        }

        @Override
        public String call() throws Exception {
            LOGGER.info("In call");
            CountDownLatch latch = null;
            synchronized (map) {
                LOGGER.info("In call: entered synchronized");
                if (map.containsKey(key)) {
                    return createResponse();
                }
                Holder newHolder = new Holder();
                latch = new CountDownLatch(1);
                newHolder.setLatch(latch);
                map.put(key, newHolder);
                LOGGER.info("In call: added new {} for key {}", newHolder, key);
            }

            LOGGER.info("In call: out of synchronized");
            LOGGER.info("In call: stating latch awaiting for {} {} ", 10, TimeUnit.SECONDS);
            latch.await(10, TimeUnit.SECONDS);
            LOGGER.info("In call: finished latch awaiting for {} {} ", 10, TimeUnit.SECONDS);
            return createResponse();
        }

        private String createResponse() {
            Holder updatedHolder = map.get(key);
            LOGGER.info("In call: retrieved existing {} for {} ", updatedHolder, key);
            String backendRequest = updatedHolder.getBackendRequest();
            return frontendRequest + ":" + backendRequest;
        }

    }

    private static final class BackendRequestTask implements Runnable {

        private final String data;
        private final String key;
        private final Map<String, Holder> map;

        public BackendRequestTask(String data, Map<String, Holder> map, String key) {
            this.data = data;
            this.map = map;
            this.key = key;
        }

        @Override
        public void run() {
            LOGGER.info("In run");
            synchronized (map) {
                LOGGER.info("In run: entered synchronized");
                if (!map.containsKey(key)) {
                    Holder newHolder = new Holder();
                    newHolder.setBackendRequest(data);
                    map.put(key, newHolder);
                    LOGGER.info("In run: added new {} for key {}", newHolder, key);
                } else {
                    Holder existing = map.get(key);
                    existing.setBackendRequest(data);
                    LOGGER.info("In run: updated existing to {} for {} ", existing, key);
                    CountDownLatch latch = existing.getLatch();
                    latch.countDown();
                    LOGGER.info("In run: latch: count down");
                }
            }
        }
    }

    private final Map<String, Holder> map = new HashMap<String, Holder>();
    private final String key = "123";
    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    
    private final Callable<String> frontendRequestTask = new FrontendRequestTask("foo", map, key);
    private final Runnable backendRequestTask = new BackendRequestTask("bar", map, key);
    
    @Test
    public void test1() throws Exception {
        // when
        Future<String> response = executor.submit(frontendRequestTask);
        executor.submit(backendRequestTask);

        // then
        assertEquals("foo:bar", response.get());
        executor.shutdown();
    }

    @Test
    public void test2() throws Exception {
        // when
        Future<String> response = executor.submit(frontendRequestTask);
        TimeUnit.SECONDS.sleep(3);
        executor.submit(backendRequestTask);

        // then
        assertEquals("foo:bar", response.get());
        executor.shutdown();
    }

    @Test
    public void test3() throws Exception {
        // when
        executor.submit(backendRequestTask);
        TimeUnit.SECONDS.sleep(3);
        Future<String> response = executor.submit(frontendRequestTask);

        // then
        assertEquals("foo:bar", response.get());
        executor.shutdown();
    }
}
