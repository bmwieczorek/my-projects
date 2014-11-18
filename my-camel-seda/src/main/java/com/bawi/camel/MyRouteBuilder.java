package com.bawi.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRouteBuilder extends RouteBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyCamelStandalone.class);

    @Override
    public void configure() throws Exception {
        from("file:inbox").routeId("myRoute")
            .process(new Processor() {
                
                @Override
                public void process(Exchange exchange) throws Exception {
                    LOGGER.info("Received in body {}", exchange.getIn().getBody());
                }
            });
    }
}