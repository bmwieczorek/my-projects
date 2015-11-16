package com.bawi.camel.onexception;

import org.apache.camel.CamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyRouteBuilderTest extends CamelTestSupport {

    private ApplicationContext springContext = new ClassPathXmlApplicationContext("onexception/my-camel-spring.xml");

    @Override
    protected CamelContext createCamelContext() throws Exception {
        return springContext.getBean(CamelContext.class);
    }

    @Test
    public void shouldHandleExceptionFromSubsystem() throws Exception {
        String response = template.requestBody(MyRouteBuilder.MY_DIRECT_START, "<RQ/>", String.class);
        assertEquals(MySubsystemExceptionHandlerProcessor.ERROR_MESSAGE, response);
    }

}
