package com.bawi.services.calculator.service;

import org.apache.log4j.Logger;

import com.bawi.services.calculator.engine.Calculator;
import com.bawi.services.calculator.jaxbtransformer.JaxbTransformer;
import com.bawi.services.calculator.model.CalculatorFault;
import com.bawi.services.calculator.model.CalculatorRQ;
import com.bawi.services.calculator.model.CalculatorRS;
import com.bawi.services.calculator.model.CalculatorServiceInterface;

public class CalculatorServiceImpl implements CalculatorServiceInterface {

    private static final Logger logger = Logger.getLogger(CalculatorServiceImpl.class);
    private Calculator calculator = new Calculator();

    // no thread safe
    private int counter = 0;

    @Override
    public CalculatorRS calculate(CalculatorRQ request) throws CalculatorFault {
        long threadId = Thread.currentThread().getId();
        if (getCounter() % 2 != 0) {
            logger.error(threadId + ": Counter is not even: " + counter);
            System.exit(0);
        }
        CalculatorRS response;
        synchronized (this) {
            counter++;
            counter++;
            // }
            // String requestXml =
            JaxbTransformer.fromJavaToXml(request);
            logger.debug(threadId + ": Processing ...");
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
        }
        return response;
    }

    public int getCounter() {
        return counter;
    }
}
