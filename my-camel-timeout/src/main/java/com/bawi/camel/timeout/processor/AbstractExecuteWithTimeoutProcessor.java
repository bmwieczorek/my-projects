package com.bawi.camel.timeout.processor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.util.ExchangeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractExecuteWithTimeoutProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractExecuteWithTimeoutProcessor.class);
    private final ExecutorService executorService;
    private TimeoutExceptionHandler timeoutExceptionHandler;
    private long timeout = 20;
    private TimeUnit timeoutUnit = TimeUnit.SECONDS;
    private boolean cancelOnTimeout = false;

    public AbstractExecuteWithTimeoutProcessor(ExecutorService executorService) {
        this.executorService = executorService;
    }
    
    @Override
    public void process(final Exchange exchange) throws Exception {
        Future<Exchange> future = executorService.submit(new Callable<Exchange>() {
            @Override
            public Exchange call() throws Exception {
                return doInProcess(exchange);
            }
        });
        waitForResponse(exchange, future);
    }

    protected abstract Exchange doInProcess(Exchange exchange) throws Exception;

    private void waitForResponse(Exchange exchange, Future<Exchange> future) throws InterruptedException,
            ExecutionException, TimeoutException {
        try {
            Exchange result = future.get(getTimeout(), getTimeoutUnit());
            if (result != null) {
                Message out = result.getOut();
                exchange.setOut(out);
                ExchangeHelper.copyResults(exchange, result);
            } else {
                exchange.setOut(exchange.getIn());
            }
        } catch (TimeoutException e) {
            if (isCancelOnTimeout()) {
                future.cancel(true);
            }
            LOGGER.warn("Exceeded processing timeout {} {}", getTimeout(), getTimeoutUnit());
            if (timeoutExceptionHandler != null) {
                timeoutExceptionHandler.handleTimeoutException(exchange);
            } else {
                throw new TimeoutException("Exceeded processing timeout " + getTimeout() + getTimeoutUnit());
            }
        }
    }

    public long getTimeout() {
        return timeout;
    }

    public TimeUnit getTimeoutUnit() {
        return timeoutUnit;
    }

    public boolean isCancelOnTimeout() {
        return cancelOnTimeout;
    }

    public void setTimeoutExceptionHandler(TimeoutExceptionHandler timeoutExceptionHandler) {
        this.timeoutExceptionHandler = timeoutExceptionHandler;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setTimeoutUnit(TimeUnit timeoutUnit) {
        this.timeoutUnit = timeoutUnit;
    }

    public void setCancelOnTimeout(boolean cancelOnTimeout) {
        this.cancelOnTimeout = cancelOnTimeout;
    }

}
