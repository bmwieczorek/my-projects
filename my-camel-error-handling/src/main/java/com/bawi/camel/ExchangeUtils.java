package com.bawi.camel;

import org.apache.camel.Exchange;

public class ExchangeUtils {

    private static final String NEW_LINE = System.getProperty("line.separator");


    public static String exchangeAsString(Exchange exchange) {
        StringBuilder message = new StringBuilder();
        message.append(exchange.getClass().getSimpleName());
        message.append("[ ");
        message.append(NEW_LINE);
        message.append("    InBody=");
        message.append(exchange.getIn().getBody());
        message.append(NEW_LINE);
        if (exchange.hasOut()) { 
            message.append("    OutBody=");
            message.append(exchange.getOut().getBody());
        }
        if (!exchange.hasOut()) {
            message.append("    Out is not set");
        }
        message.append(NEW_LINE);
        message.append("    properties=");
        message.append(exchange.getProperties());
        message.append(NEW_LINE);
        message.append("    InHeaders=");
        message.append(exchange.getIn().getHeaders());
        message.append(NEW_LINE);
        if (exchange.hasOut()) { 
            message.append("    OutHeaders=");
            message.append(exchange.getOut().getHeaders());
            message.append(NEW_LINE);
        }
        message.append("    exception=");
        message.append(exchange.getException());
        message.append(NEW_LINE);
        message.append("    pattern=");
        message.append(exchange.getPattern());
        message.append(NEW_LINE);
        message.append("]");
        return message.toString();
    }

    public static Exception getException(Exchange exchange) {
        Exception exception = exchange.getException();
        return exception != null ? exception : exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
    }
}
