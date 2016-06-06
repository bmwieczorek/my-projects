package com.bawi.spring.boot.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MySpringBootWebApp {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootWebApp.class, args);
    }

    @RestController
    static class MyController {

        private static final Logger LOGGER = LoggerFactory.getLogger(MySpringBootWebApp.class);

        @RequestMapping("/")
        public String handle(@RequestParam String airline, @RequestParam int sleepSeconds) throws InterruptedException {
            LOGGER.info("Starting to sleep {} seconds", sleepSeconds);
            if (sleepSeconds == -1) {
                throw new IllegalArgumentException("-1");
            }
            TimeUnit.SECONDS.sleep(sleepSeconds);
            LOGGER.info("Finished sleeping {} seconds", sleepSeconds);
            return "DONE";
        }
    }

    @ConditionalOnProperty("throttling.enabled")
    @Component
    static class RequestThrottler extends OncePerRequestFilter {

        private static final Logger LOGGER = LoggerFactory.getLogger(RequestThrottler.class);

        private final Map<String, Integer> airlineToRequestsCount = new ConcurrentHashMap<>();

        @Value("${throttling.request.count.limit}")
        int requestCountLimit;

        @Value("${throttling.request.parameter.name}")
        String requestParameterName;


        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

            LOGGER.info("Start processing request: {}", request.getRequestURL() + "?" + request.getQueryString());
            String parameterValue = request.getParameter(requestParameterName);

            if (parameterValue == null) {
                LOGGER.debug("Skipping throttling as missing parameter {} in the request", requestParameterName);
                filterChain.doFilter(request, response);
                return;
            }

            try {
                incrementRequestCount(parameterValue);

                filterChain.doFilter(request, response);

                decrementRequestsCount(parameterValue);
            }
            catch (RequestThrottledException e) {
                setThrottledResponse(response, e.getMessage());
            }
            catch (Exception e) {
                decrementRequestsCount(parameterValue);
            }

        }

        private void setThrottledResponse(HttpServletResponse response, String message) throws IOException {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(message.getBytes());
        }

        private void decrementRequestsCount(String currentAirline) {
            airlineToRequestsCount.compute(currentAirline, (parameterName, currentRequestCount) -> {
                currentRequestCount = currentRequestCount - 1;
                LOGGER.debug("Decremented concurrently processed requests count to {} for parameter {}", currentRequestCount, currentAirline);
                return currentRequestCount;
            });
        }

        private void incrementRequestCount(String parameterName) {
            airlineToRequestsCount.compute(parameterName, (airline, currentRequestCount) -> {
                if (currentRequestCount == null) {
                    currentRequestCount = 0;
                }
                if (currentRequestCount == requestCountLimit) {
                    String message = "Rejecting request as exceeding throttling limit of " + requestCountLimit + " of concurrently processed requests for url parameter " + parameterName;
                    LOGGER.warn(message);
                    throw new RequestThrottledException(message);
                }
                ++currentRequestCount;
                LOGGER.debug ("Incremented concurrently processed requests count to {} for parameter {}", currentRequestCount, parameterName);
                return currentRequestCount;
            });
        }

        public void setRequestCountLimit(int requestCountLimit) {
            this.requestCountLimit = requestCountLimit;
        }
    }

    static class RequestThrottledException extends RuntimeException {
        RequestThrottledException(String message) {
            super(message);
        }
    }
}
