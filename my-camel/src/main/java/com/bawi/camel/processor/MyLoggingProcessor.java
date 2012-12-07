package com.bawi.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MyLoggingProcessor implements Processor {

    public static final String MY_PROCESSED_PROPERTY = "PROCESSED";

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("In body: " + exchange.getIn().getBody());
        System.out.println("File name: " + exchange.getIn().getHeader(Exchange.FILE_NAME));
        System.out.println("Out body: " + exchange.getOut().getBody());
        exchange.setProperty(MY_PROCESSED_PROPERTY, true);
        exchange.setOut(exchange.getIn());
    }

}
