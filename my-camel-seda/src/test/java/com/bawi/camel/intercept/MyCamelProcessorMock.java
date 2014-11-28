package com.bawi.camel.intercept;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCamelProcessorMock extends CamelTestSupport {

    static final Logger LOGGER = LoggerFactory.getLogger(MyCamelProcessorMock.class);
    
    static class LogginProcessor implements Processor {
        final String label;

        public LogginProcessor(String label) {
            this.label = label;
        }

        @Override
        public void process(Exchange exchange) throws Exception {
            LOGGER.info("{} Exchange InBody={}, InHeaders={}, properties={}, thread={}", new Object[] {label, exchange.getIn().getBody(), exchange.getIn().getHeaders(), exchange.getProperties(), Thread.currentThread()});
        }
    }

    static class MyRouteBuilder extends RouteBuilder {

        @Override
        public void configure() throws Exception {
            from("direct:start").
                routeId("start").
                process(new LogginProcessor("before mySubRoute")).id("before").
                to("direct:mySubRoute").
                process(new LogginProcessor("after mySubRoute")).id("after");

            from("direct:mySubRoute").
                routeId("mySubRoute").
                process(new LogginProcessor("in mySubRoute")).id("in");
        }

    }

    @Produce(uri = "direct:start")
    protected ProducerTemplate producerTemplate;

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new MyRouteBuilder();
    }

    @Test
    public void shouldRemoveProcessorFromRoute() throws Exception { 
        // given
        RouteDefinition routeDefinition = context.getRouteDefinition("start");
        routeDefinition.adviceWith(context, new AdviceWithRouteBuilder() {
            
            @Override
            public void configure() throws Exception {
                weaveById("before").remove();
            }
        });
        

        Exchange exchange = new DefaultExchange(new DefaultCamelContext());
        // when
        producerTemplate.send(exchange);
        // then
    }

    @Test
    public void shouldReplaceProcessorWithIdToMockFromRoute() throws Exception { 
        // given
        RouteDefinition routeDefinition = context.getRouteDefinition("start");
        routeDefinition.adviceWith(context, new AdviceWithRouteBuilder() {
            
            @Override
            public void configure() throws Exception {
                weaveById("before").replace().to("mock:a");
            }
        });
        getMockEndpoint("mock:a").expectedMessageCount(1);

        Exchange exchange = new DefaultExchange(new DefaultCamelContext());
        // when
        producerTemplate.send(exchange);
        // then
        assertMockEndpointsSatisfied();
    }

}
