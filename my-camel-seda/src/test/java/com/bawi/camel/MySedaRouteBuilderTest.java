package com.bawi.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MySedaRouteBuilderTest extends CamelTestSupport {
    
    private ApplicationContext springContext = new ClassPathXmlApplicationContext("classpath:my-seda-camel-spring.xml");

    @Override
    protected CamelContext createCamelContext() throws Exception {
        return springContext.getBean(CamelContext.class);
    }

    @Test
    public void testName() throws Exception {
        for (int i = 0; i < 100000; i++) {
            template.sendBody(MySedaRouteBuilder.MY_SEDA_ENDPOINT, "Hello" + i);
        }
        Thread.sleep(10000);
    }

}
