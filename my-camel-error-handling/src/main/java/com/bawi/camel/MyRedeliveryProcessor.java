package com.bawi.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRedeliveryProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRedeliveryProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.info("Removing property {} from exchange={}", Exchange.EXCEPTION_CAUGHT, ExchangeUtils.exchangeAsString(exchange));
        exchange.removeProperty(Exchange.EXCEPTION_CAUGHT);
    }

}
