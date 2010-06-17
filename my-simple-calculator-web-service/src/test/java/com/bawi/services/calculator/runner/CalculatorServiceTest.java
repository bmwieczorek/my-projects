package com.bawi.services.calculator.runner;

import junit.framework.Assert;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import com.bawi.services.calculator.model.CalculatorRQ;
import com.bawi.services.calculator.model.CalculatorRS;
import com.bawi.services.calculator.model.CalculatorServiceInterface;
import com.bawi.services.calculator.model.Operation;

public class CalculatorServiceTest {

    private CalculatorServiceInterface calculatorService = createServiceClient(
            "http://localhost:7890/calculator", CalculatorServiceInterface.class);

    @Test
    public void shouldCalculateAddition() throws Exception {
        // given
        Thread serviceRunner = createServiceRunnerThread();
        serviceRunner.start();
        CalculatorRQ request = new CalculatorRQ().withOperation(Operation.ADD).withParameters(1, 2);
        Thread.sleep(3000);

        // when
        CalculatorRS response = calculatorService.calculate(request);

        // then
        Assert.assertEquals(3, response.getResult());
    }

    @SuppressWarnings("unchecked")
    public static <T> T createServiceClient(String address, Class<T> clazz) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setAddress(address);
        factory.setServiceClass(clazz);
        return (T) factory.create();
    }

    private Thread createServiceRunnerThread() {
        Thread serviceRunner = new Thread(new Runnable() {
            @Override
            public void run() {
                ServiceRunner.main(null);
            }
        });
        return serviceRunner;
    }

}
