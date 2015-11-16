package com.bawi.camel.redelivery;

import org.apache.camel.CamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bawi.camel.redelivery.MyRouteBuilder;


public class MyRouteBuilderTest extends CamelTestSupport {

    private ApplicationContext springContext = new ClassPathXmlApplicationContext("redelivery/my-camel-spring.xml");

    @Override
    protected CamelContext createCamelContext() throws Exception {
        return springContext.getBean(CamelContext.class);
    }

    @Test
    public void testName() throws Exception {
        String requestBody = template.requestBody(MyRouteBuilder.MY_DIRECT_START, "<RQ/>", String.class);
        System.out.println(requestBody);
        Thread.sleep(10000);
    }

}
