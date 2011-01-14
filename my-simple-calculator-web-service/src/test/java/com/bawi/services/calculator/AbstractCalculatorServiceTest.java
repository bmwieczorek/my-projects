package com.bawi.services.calculator;

import junit.framework.Assert;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import com.bawi.services.calculator.model.CalculatorRQ;
import com.bawi.services.calculator.model.CalculatorRS;
import com.bawi.services.calculator.model.CalculatorServiceInterface;
import com.bawi.services.calculator.model.Operation;
import com.bawi.test.connection.UrlConnectionChecker;

public abstract class AbstractCalculatorServiceTest {

    private static final String SERVICE_URL = "http://localhost:7890/calculator";

    private CalculatorServiceInterface calculatorService = createServiceClient(SERVICE_URL,
            CalculatorServiceInterface.class);

    private UrlConnectionChecker connectionChecker = UrlConnectionChecker.createWithTimeOut(5);

    @Test
    public void shouldCalculateAddition() throws Exception {
        // given
        CalculatorRQ request = new CalculatorRQ().withOperation(Operation.ADD).withParameters(1, 2);
        Assert.assertTrue(connectionChecker.isUp(SERVICE_URL + "?wsdl"));

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

}
