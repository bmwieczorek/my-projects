package com.bawi.camel.onexception;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySubsystemExceptionHandlerProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySubsystemExceptionHandlerProcessor.class);
    static final String ERROR_MESSAGE = "My error";


    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.info("Setting body from {} to {}", exchange.getIn().getBody(), ERROR_MESSAGE);
        exchange.getIn().setBody(ERROR_MESSAGE);
    }

}
