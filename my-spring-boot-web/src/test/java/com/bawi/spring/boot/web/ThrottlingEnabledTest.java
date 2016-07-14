package com.bawi.spring.boot.web;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

@SpringApplicationConfiguration(MySpringBootWebApp.class)
@SuppressWarnings("unchecked")
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = { "throttling.enabled = true", "throttling.request.count.limit = 1"} )
public class ThrottlingEnabledTest extends ThrottlingTestBase {

    @Autowired
    Environment environment;

    @Autowired
    MySpringBootWebApp.RequestThrottler requestThrottler;

    @Test
    public void shouldThrottleRequestsExceedingLimit() throws InterruptedException {
        // given
        Assume.assumeTrue(Boolean.valueOf(environment.getProperty("throttling.enabled")));
        requestThrottler.setRequestCountLimit(1);

        // when
        List<Pair<String, HttpStatus>> airlineResponseStatuses = sendAsyncRequestsAirlinesCF(
                "VX", "VX", "VX",
                "AA", "AA",
                "AZ").join();

        // then
        assertThat(airlineResponseStatuses, containsInAnyOrder(
                new Pair<>("VX", OK), new Pair<>("VX", TOO_MANY_REQUESTS), new Pair<>("VX", TOO_MANY_REQUESTS),
                new Pair<>("AA", OK), new Pair<>("AA", TOO_MANY_REQUESTS),
                new Pair<>("AZ", OK)));
    }

    @Test
    public void shouldThrottleRequestsAllRequests() throws InterruptedException {
        // given
        requestThrottler.setRequestCountLimit(0);

        // when
        List<Pair<String, HttpStatus>> airlineResponseStatuses = sendAsyncRequestsAirlines(
                "VX", "VX", "VX",
                "AA", "AA",
                "AZ");

        // then
        assertThat(airlineResponseStatuses, containsInAnyOrder(
                new Pair<>("VX", TOO_MANY_REQUESTS), new Pair<>("VX", TOO_MANY_REQUESTS), new Pair<>("VX", TOO_MANY_REQUESTS),
                new Pair<>("AA", TOO_MANY_REQUESTS), new Pair<>("AA", TOO_MANY_REQUESTS),
                new Pair<>("AZ", TOO_MANY_REQUESTS)));
    }


}
