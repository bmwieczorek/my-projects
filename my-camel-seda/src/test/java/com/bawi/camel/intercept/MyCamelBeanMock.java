package com.bawi.camel.intercept;

import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockComponent;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCamelBeanMock extends CamelTestSupport {

    static final Logger LOGGER = LoggerFactory.getLogger(MyCamelSpringBeanMock.class);
    
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

//                bean(new SpringLoggingBean()). // bean() does not create endpoint nor component
                // component creates an endpoint that is referred by URI (e.g.: bean:beanName bean meaning bean component)

//                process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        // TODO Auto-generated method stub
//                        
//                    }}).
                // processor is a exchange consumer or message translator

                to("bean:springLoggingBean").
                log("the end");
        }

    }

    @Produce(uri = "direct:start")
    protected ProducerTemplate producerTemplate;

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new MySpringRouteBuilder();
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();
        camelContext.removeComponent("bean");
        camelContext.addComponent("bean", new MockComponent());
        return camelContext;
    }

    @Test
    public void shouldInterceptMyRoute() throws Exception { 
        // given
        System.out.println(context.getComponentNames());
        System.out.println(context.getEndpointMap());
        MockEndpoint mockEndpoint = getMockEndpoint("bean:springLoggingBean");
        mockEndpoint.expectedMessageCount(1);
        // when
        producerTemplate.sendBody("Hello");
        // then
        assertMockEndpointsSatisfied();
    }


}
