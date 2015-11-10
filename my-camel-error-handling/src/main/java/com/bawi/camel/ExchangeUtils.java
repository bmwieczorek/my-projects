package com.bawi.camel;

import org.apache.camel.Exchange;

public class ExchangeUtils {

    public static String exchangeAsString(Exchange exchange) {
        return "InBody=" + exchange.getIn().getBody() +
               ", InHeaders=" + exchange.getIn().getHeaders() +
               ", Properties=" + exchange.getProperties();
    }

    public static Exception getException(Exchange exchange) {
        Exception exception = exchange.getException();
        return exception != null ? exception : exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
    }
}
