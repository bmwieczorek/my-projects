package com.bawi.spring.boot.web;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(MySpringBootWebApp.class)
@WebIntegrationTest({"server.port = 0"})
public class ThrottlingTestBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThrottlingDisabledTest.class);

    RestTemplate restTemplate = new TestRestTemplate();

    @Value("${local.server.port}") // random port as triggered by server.port = 0
    int port;

    List<Pair<String, HttpStatus>> sendAsyncRequestsAirlines(String... airlinesArray) throws InterruptedException {
        List<String> airlines = Arrays.asList(airlinesArray);
        ExecutorService executor = Executors.newFixedThreadPool(airlines.size());

//                List<CompletableFuture<HttpStatus>> completableFutures = airlines
//                .stream()
//                .map(airline -> CompletableFuture.supplyAsync(() -> airlineRequestEntity(airline), executor))
//                .map(cf -> cf.thenCompose(responseEntity -> CompletableFuture.supplyAsync(() -> responseEntity.getStatusCode(), executor)))
//                .collect(Collectors.toList());

// or

//        List<CompletableFuture<HttpStatus>> completableFutures = airlines
//                .stream()
//                .map(airline -> CompletableFuture.supplyAsync(() -> airlineRequestEntity(airline), executor))
//                .map(cf -> cf.thenApplyAsync(ResponseEntity::getStatusCode, executor))
//                .collect(Collectors.toList());

// or

        List<CompletableFuture<Pair<String, HttpStatus>>> completableFutures = airlines
                .stream()
                .map(airline -> CompletableFuture.supplyAsync(() -> new Pair<>(airline, getForEntity(airline)), executor)
                                                 .thenApply(airlineRespEntity -> new Pair<>(airlineRespEntity.p1, airlineRespEntity.p2.getStatusCode()))
                )
                .collect(Collectors.toList());

        System.out.println("Created completable futures");

        Thread.sleep(2000);
        System.out.println("Finished sleeping");

        return completableFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    ResponseEntity<String> getForEntity(String airline) {
        LOGGER.info("Requesting entity for airline {}", airline);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "?airline=" + airline + "&sleepSeconds=1", String.class);
        LOGGER.info("Received response entity {} for airline {}", responseEntity, airline);
        return responseEntity;
    }

    static class Pair<P1, P2> {
        final P1 p1;
        final P2 p2;

        public Pair(P1 p1, P2 p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public String toString() {
            return "Pair{" + p1 + ", " + p2 + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return p1.equals(pair.p1) && p2.equals(pair.p2);
        }

        @Override
        public int hashCode() {
            int result = p1.hashCode();
            result = 31 * result + p2.hashCode();
            return result;
        }
    }
}
