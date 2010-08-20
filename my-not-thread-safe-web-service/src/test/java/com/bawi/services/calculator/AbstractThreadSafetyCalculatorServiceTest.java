package com.bawi.services.calculator;

import junit.framework.Assert;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import com.bawi.services.calculator.model.CalculatorFault;
import com.bawi.services.calculator.model.CalculatorRQ;
import com.bawi.services.calculator.model.CalculatorServiceInterface;
import com.bawi.services.calculator.model.Operation;
import com.bawi.test.connection.UrlConnectionChecker;

public abstract class AbstractThreadSafetyCalculatorServiceTest {

    private static final String serviceUrl = "http://localhost:7890/calculator";

    private CalculatorServiceInterface calculatorService = createServiceClient(serviceUrl,
            CalculatorServiceInterface.class);

    private UrlConnectionChecker connectionChecker = UrlConnectionChecker.createWithTimeOut(5);

    @Test
    public void shouldBeThreadSafe() throws Exception {

        Assert.assertTrue(connectionChecker.isUp(serviceUrl + "?wsdl"));

        final CalculatorRQ request = new CalculatorRQ().withOperation(Operation.ADD).withParameters(1, 2);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        calculatorService.calculate(request);
                    } catch (CalculatorFault e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
        Thread.sleep(50000);

    }

    @SuppressWarnings("unchecked")
    public static <T> T createServiceClient(String address, Class<T> clazz) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setAddress(address);
        factory.setServiceClass(clazz);
        return (T) factory.create();
    }

}
