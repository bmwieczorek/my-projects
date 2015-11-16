package com.bawi.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySubSystemProcessor implements Processor {

    private static int counter = 1;
    private static final Logger LOGGER = LoggerFactory.getLogger(MySubSystemProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.info("In MySubsystem, request counter={}, exchange={}", counter, ExchangeUtils.exchangeAsString(exchange));
        String request = exchange.getIn().getBody(String.class);
        LOGGER.info("In MySubsystem, received request: {}", request);

        String response = "<RS error=\"true\">" + request + "</RS>";
        LOGGER.info("In MySubsystem, setting up reponse: {}", response);
        exchange.getIn().setBody(response);

//        if (counter % 2 == 1) {
            counter++;
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Exception from MySubSystemProcessor");
            LOGGER.info("In MySubsystem, throwing: {}", illegalArgumentException.getMessage());
            throw illegalArgumentException;
//        } 
//        counter++;
    }

}
