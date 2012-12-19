package com.bawi.camel;

import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyCamelMockitoSpringRouteTest extends CamelSpringTestSupport {

    @Override
    protected AbstractXmlApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("camel-mockito-spring-context.xml");
    }

    @Test
    public void shouldDecorateText() throws Exception {
        // given
        BIface b = (BIface)applicationContext.getBean("b", BIface.class);
        Mockito.when(b.addMinuses("=a")).thenReturn("=abb");
 
        // when
        String reply = template.requestBody(MyRouteMockitoBuilder.FROM, "=", String.class);
        Thread.sleep(1000);

        // then
        Assert.assertEquals("=abb", reply);

    }
}
