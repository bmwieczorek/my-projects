package com.bawi.camel.redelivery;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bawi.camel.ExchangeUtils;

public class MyOnRedeliveryProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyOnRedeliveryProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.info("Removing property {} from exchange={}", Exchange.EXCEPTION_CAUGHT, ExchangeUtils.exchangeAsString(exchange));
        exchange.removeProperty(Exchange.EXCEPTION_CAUGHT);

       if (exchange.hasOut()) {
           LOGGER.info("Setting out body from {} to {} based on in body", exchange.getOut().getBody(), exchange.getIn().getBody());
           exchange.getOut().setBody(exchange.getIn().getBody());
       }
    }

}
