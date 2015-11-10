package com.bawi.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MyRouteBuilderTest extends CamelTestSupport {

    private ApplicationContext springContext = new ClassPathXmlApplicationContext("classpath:my-camel-spring.xml");

    @Override
    protected CamelContext createCamelContext() throws Exception {
        return (CamelContext) springContext.getBean("myCamelContext", CamelContext.class);
    }

    @Test
    public void testName() throws Exception {
        template.sendBody(MyRouteBuilder.MY_DIRECT_START, "Hello");
        Thread.sleep(10000);
    }

}
