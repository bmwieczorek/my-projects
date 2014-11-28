package com.bawi.camel.intercept;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyCamelSpringBeanMock extends CamelTestSupport {

    static final Logger LOGGER = LoggerFactory.getLogger(MyCamelBeanMock.class);
    
    public static class SpringLoggingBean  {

        public void log(String body) {
            LOGGER.info("Body={}", body);
        }
    }

    public static class MySpringRouteBuilder extends RouteBuilder {

        @Override
        public void configure() throws Exception {
            from("direct:start").
                routeId("start").
                to("bean:springLoggingBean").
                log("the end");
        }

    }
    
    private ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("classpath:my-spring-beans.xml");

    @Produce(uri = "direct:start")
    protected ProducerTemplate producerTemplate;

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new MySpringRouteBuilder();
    }
    
    @Override
    protected CamelContext createCamelContext() throws Exception {
        return springContext.getBean(CamelContext.class);
    }

    @Test
    public void shouldInterceptMyRoute() throws Exception { 
        Map<String, Endpoint> endpointMap = context.getEndpointMap();
        System.out.println(endpointMap);
        // given
        RouteDefinition routeDefinition = context.getRouteDefinition("start");
        routeDefinition.adviceWith(context, new AdviceWithRouteBuilder() {

            @Override
            public void configure() throws Exception {
                //mockEndpoints();
                mockEndpointsAndSkip("bean:*");
            }

        });
        System.out.println(context.getEndpointMap());
        System.out.println(context.getComponentNames());
        // when
        producerTemplate.sendBody("Hello");
        // then
    }
    
}
