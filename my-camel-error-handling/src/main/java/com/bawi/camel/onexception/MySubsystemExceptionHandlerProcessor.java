package com.bawi.camel.onexception;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySubsystemExceptionHandlerProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySubsystemExceptionHandlerProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        String newMessage = "My error";
        LOGGER.info("Setting body {} from to {}", exchange.getIn().getBody(String.class), newMessage);
        exchange.getIn().setBody(newMessage);
    }

}
