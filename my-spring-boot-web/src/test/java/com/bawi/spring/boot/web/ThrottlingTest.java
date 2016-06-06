package com.bawi.spring.boot.web;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(MySpringBootWebApp.class)
@WebIntegrationTest({"server.port = 0"})
@TestPropertySource(properties = { "throttling.enabled = true", "throttling.request.count.limit = 1"} )
public class ThrottlingTest {

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
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return p1.equals(pair.p1) && p2.equals(pair.p2);
        }

        @Override
        public int hashCode() {
            int result = p1.hashCode();
            result = 31 * result + p2.hashCode();
            return result;
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ThrottlingTest.class);

    final RestTemplate restTemplate = new TestRestTemplate();

    @Value("${local.server.port}") // random port as triggered by server.port = 0
    int port;

    @Autowired
    Environment environment;

//    @Autowired
//    MySpringBootWebApp.RequestThrottler requestThrottler;

    @Test
    public void should() throws InterruptedException {
        // given

        // when
        System.out.println(environment.getProperty("throttling.enabled"));
        System.out.println(environment.getProperty("throttling.request.count.limit"));
        System.out.println(environment.getProperty("server.port"));
        System.out.println(environment.getProperty("local.server.port"));
        System.out.println(port);

        // then
        // requestThrottler.setRequestCountLimit(1);

        List<String> airlines = asList("VX", "VX", "AA", "AZ", "VX", "AA");
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

        List<Pair<String, HttpStatus>> airlineStatuses = completableFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        assertThat(airlineStatuses, Matchers.containsInAnyOrder(new Pair<>("VX", OK),
                                                                new Pair<>("VX", TOO_MANY_REQUESTS),
                                                                new Pair<>("VX", TOO_MANY_REQUESTS),

                                                                new Pair<>("AA", OK),
                                                                new Pair<>("AA", TOO_MANY_REQUESTS),

                                                                new Pair<>("AZ", OK)));
    }

    private ResponseEntity<String> getForEntity(String airline) {
        LOGGER.info("Requesting entity for airline {}", airline);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "?airline=" + airline + "&sleepSeconds=1", String.class);
        LOGGER.info("Received response entity {} for airline {}", responseEntity, airline);
        return responseEntity;
    }
}
