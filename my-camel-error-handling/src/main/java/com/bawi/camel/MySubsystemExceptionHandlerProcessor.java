package com.bawi.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySubsystemExceptionHandlerProcessor implements Processor {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MySubsystemExceptionHandlerProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.debug("Handling exception for exchange={}", ExchangeUtils.exchangeAsString(exchange));

        Boolean isRetry = exchange.getProperty(MyRedeliveryProcessor.IS_RETRY, Boolean.class);
        if (isRetry == null || !isRetry) {
            Exception exception = ExchangeUtils.getException(exchange);
            LOGGER.warn("Rethrowing exception: {}", exception.getMessage());
            throw exception;
        } 

        LOGGER.debug("Removing property: {}", MyRedeliveryProcessor.IS_RETRY);
        exchange.removeProperty(MyRedeliveryProcessor.IS_RETRY);

        String body = "Retry failed";
        LOGGER.debug("Setting body to: {}", body);
        exchange.getIn().setBody(body);
        throw new IllegalArgumentException("Retry ...");
    }
}