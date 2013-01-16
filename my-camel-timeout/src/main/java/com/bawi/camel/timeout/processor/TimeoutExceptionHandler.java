package com.bawi.camel.timeout.processor;

import org.apache.camel.Exchange;

public interface TimeoutExceptionHandler {
    void handleTimeoutException(Exchange exchange);
}
