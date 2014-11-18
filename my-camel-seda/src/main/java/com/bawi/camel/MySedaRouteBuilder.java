package com.bawi.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySedaRouteBuilder extends RouteBuilder {

    public static final String MY_SEDA_ROUTE = "mySedaRoute";
    public static final String MY_SEDA_ENDPOINT = "seda:input?blockWhenFull=true&size=1000&concurrentConsumers=10";

    private static final Logger LOGGER = LoggerFactory.getLogger(MyCamelStandalone.class);
    
    private int maximumRequestCount;

    @Override
    public void configure() throws Exception {

        from(MY_SEDA_ENDPOINT).routeId(MY_SEDA_ROUTE)
            .throttle(createMaximumRequestCountExpression()).timePeriodMillis(1000) // allow processing of maximumRequestCount rq every timePeriodMillis
            .process(new Processor() {

                @Override
                public void process(Exchange exchange) throws Exception {
                    LOGGER.info("Received in-body {}", exchange.getIn().getBody());
                    Thread.sleep(1000);
                }

            });
    }

    private Expression createMaximumRequestCountExpression() {
        return new Expression() {
            
            @Override
            public <T> T evaluate(Exchange exchange, Class<T> type) {
                return type.cast(getMaximumRequestCount());
            }
        };
    }

    public void setMaximumRequestCount(int maximumRequestCount) {
        LOGGER.info("Setting maximumRequestCount={}", maximumRequestCount);
        this.maximumRequestCount = maximumRequestCount;
    }

    public int getMaximumRequestCount() {
        return maximumRequestCount;
    }
}