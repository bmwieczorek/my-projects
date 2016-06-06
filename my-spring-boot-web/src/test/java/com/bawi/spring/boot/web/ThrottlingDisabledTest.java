package com.bawi.spring.boot.web;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.OK;

@SuppressWarnings("unchecked")
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = { "throttling.enabled = false" } )
public class ThrottlingDisabledTest extends ThrottlingTestBase {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void shouldNotThrottleWhenDisabled() throws InterruptedException {
        // given
        Assume.assumeFalse(applicationContext.containsBean(MySpringBootWebApp.RequestThrottler.class.getName()));

        // when
        List<Pair<String, HttpStatus>> airlineStatuses = sendAsyncRequestsAirlines(
                "VX", "VX", "VX",
                "AA", "AA",
                "AZ");

        // then
        assertThat(airlineStatuses, containsInAnyOrder(
                new Pair<>("VX", OK), new Pair<>("VX", OK), new Pair<>("VX", OK),
                new Pair<>("AA", OK), new Pair<>("AA", OK),
                new Pair<>("AZ", OK)));
    }

}
