package com.bawi.services.calculator.service;

import java.util.Arrays;

import org.apache.log4j.Logger;

import com.bawi.services.calculator.engine.Calculator;
import com.bawi.services.calculator.jaxbtransformer.JaxbTransformer;
import com.bawi.services.calculator.model.CalculatorFault;
import com.bawi.services.calculator.model.CalculatorNotThreadSafeRQ;
import com.bawi.services.calculator.model.CalculatorRQ;
import com.bawi.services.calculator.model.CalculatorRS;
import com.bawi.services.calculator.model.CalculatorServiceInterface;
import com.bawi.services.calculator.model.CalculatorThreadSafeRQ;

public class CalculatorServiceImpl implements CalculatorServiceInterface {

    private static final String LONG_STRING = createString(10000);
    private static final Logger LOGGER = Logger.getLogger(CalculatorServiceImpl.class);
    private Calculator calculator = new Calculator();

    // no thread safe
    private int counter = 0;

    @Override
    public CalculatorRS calculateThreadSafe(CalculatorThreadSafeRQ request) throws CalculatorFault {
        long threadId = Thread.currentThread().getId();
        exitWhenCounterIsNotEven(threadId);
        CalculatorRS response;
        // synchronized (this) {
        // counter++;
        // counter++;
        // }
        response = calculateResponse(request);
        return response;
    }

    @Override
    public CalculatorRS calculateNotThreadSafe(CalculatorNotThreadSafeRQ request) throws CalculatorFault {
        long threadId = Thread.currentThread().getId();
        exitWhenCounterIsNotEven(threadId);
        CalculatorRS response;
        counter++;
        counter++;
        response = calculateResponse(request);
        return response;
    }

    private CalculatorRS calculateResponse(CalculatorRQ request) {
        CalculatorRS response;
        // }
        // String requestXml =
        JaxbTransformer.fromJavaToXml(request);

        // logger.debug(threadId + ": Processing ... " + createString(10000));
        LOGGER.debug(LONG_STRING);
        // if (logger.isDebugEnabled()) {
        // logger.debug("Request valid:" + requestXml);
        // }
        // int result =
        calculator.calculate(request.getOperation(), request.getParameters());
        response = new CalculatorRS().withResult(counter);
        // String responseXml =
        JaxbTransformer.fromJavaToXml(response);
        // if (logger.isDebugEnabled()) {
        // logger.debug("Response valid:" + responseXml);
        return response;
    }

    private static String createString(int n) {
        char[] arr = new char[n];
        Arrays.fill(arr, 0, n - 1, 'x');
        return new String(arr);
    }

    private void exitWhenCounterIsNotEven(long threadId) throws CalculatorFault {
        if (getCounter() % 2 != 0) {
            String message = threadId + ": Counter is not even: " + counter;
            LOGGER.error(message);
            throw new CalculatorFault("Thread safety violation: " + message);
        }
    }

    public int getCounter() {
        return counter;
    }

}