package com.bawi.services.calculator.threadsafety;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.soap.SOAPFaultException;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.hamcrest.Description;
import org.junit.Test;
import org.junit.matchers.TypeSafeMatcher;

import com.bawi.services.calculator.model.CalculatorFault;
import com.bawi.services.calculator.model.CalculatorNotThreadSafeRQ;
import com.bawi.services.calculator.model.CalculatorRQ;
import com.bawi.services.calculator.model.CalculatorRS;
import com.bawi.services.calculator.model.CalculatorServiceInterface;
import com.bawi.services.calculator.model.CalculatorThreadSafeRQ;
import com.bawi.services.calculator.model.Operation;
import com.bawi.test.connection.UrlConnectionChecker;

public abstract class AbstractThreadSafetyCalculatorServiceTest {

    private static final int TEST_DURATION_IN_SECONDS = 3;

    private static final int THREADS_COUNT = 10;

    private static final String serviceUrl = "http://localhost:7890/calculator";

    private final CalculatorServiceInterface calculatorService = createServiceClient(serviceUrl,
            CalculatorServiceInterface.class);

    private UrlConnectionChecker connectionChecker = UrlConnectionChecker.createWithTimeOut(5);

    private List<SOAPFaultException> soapFaults = new ArrayList<SOAPFaultException>();
    private List<Exception> otherExceptions = new ArrayList<Exception>();

    private static interface CalculatorCalback {
        CalculatorRS calculate(CalculatorRQ calculatorRQ) throws CalculatorFault;
    }

    private class ThreadSafeCalculatorCallback implements CalculatorCalback {
        @Override
        public CalculatorRS calculate(CalculatorRQ rq) throws CalculatorFault {
            CalculatorThreadSafeRQ calculatorThreadSafeRQ = new CalculatorThreadSafeRQ().withOperation(
                    rq.getOperation()).withParameters(rq.getParameters());
            return calculatorService.calculateThreadSafe(calculatorThreadSafeRQ);
        }
    }

    private class NotThreadSafeCalculatorCallback implements CalculatorCalback {
        @Override
        public CalculatorRS calculate(CalculatorRQ rq) throws CalculatorFault {
            CalculatorNotThreadSafeRQ calculatorNotThreadSafeRQ = new CalculatorNotThreadSafeRQ()
                    .withOperation(rq.getOperation()).withParameters(rq.getParameters());
            return calculatorService.calculateNotThreadSafe(calculatorNotThreadSafeRQ);
        }
    }

    @Test
    public void shouldBeThreadSafe() throws Exception {
        // given
        waitUntilServiceIsStarted();

        // when
        sendRequestsToThreadSafeService();

        // then
        assertEquals(0, soapFaults.size());
        assertEquals(0, otherExceptions.size());
    }

    @Test
    public void shouldNotBeThreadSafe() throws Exception {
        // given
        waitUntilServiceIsStarted();

        // when
        sendRequestsToNotThreadSafeService();

        // then
        assertThat(soapFaults, haveMessage("Thread safety violation"));
        assertEquals(0, otherExceptions.size());
    }

    private void waitUntilServiceIsStarted() throws Exception {
        assertTrue(connectionChecker.isUp(serviceUrl + "?wsdl"));
    }

    private void sendRequestsToThreadSafeService() throws InterruptedException {
        startThreadsSendingCalculatorRequests(new ThreadSafeCalculatorCallback());
    }

    private void sendRequestsToNotThreadSafeService() throws InterruptedException {
        startThreadsSendingCalculatorRequests(new NotThreadSafeCalculatorCallback());
    }

    private void startThreadsSendingCalculatorRequests(final CalculatorCalback calculatorCallback)
            throws InterruptedException {
        final CalculatorRQ request = new CalculatorRQ().withOperation(Operation.ADD).withParameters(1, 2);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        calculatorCallback.calculate(request);
                    } catch (SOAPFaultException e) {
                        soapFaults.add(e);
                    } catch (Exception e) {
                        otherExceptions.add(e);
                    }
                }
            }
        };
        for (int i = 0; i < THREADS_COUNT; i++) {
            new Thread(runnable).start();
        }
        Thread.sleep(1000 * TEST_DURATION_IN_SECONDS);
    }

    private TypeSafeMatcher<List<SOAPFaultException>> haveMessage(final String text) {
        return new TypeSafeMatcher<List<SOAPFaultException>>() {

            @Override
            public void describeTo(Description arg0) {

            }

            @Override
            public boolean matchesSafely(List<SOAPFaultException> soapFaultExceptions) {
                for (SOAPFaultException soapFaultException : soapFaultExceptions) {
                    return soapFaultException.getMessage().contains(text);
                }
                return false;
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static <T> T createServiceClient(String address, Class<T> clazz) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setAddress(address);
        factory.setServiceClass(clazz);
        return (T) factory.create();
    }

}
