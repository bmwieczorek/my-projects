package com.bawi.camel.wiretap;

import java.util.concurrent.TimeUnit;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyWireTapCamelTest extends CamelTestSupport {

    static final Logger LOGGER = LoggerFactory.getLogger(MyWireTapCamelTest.class);
    
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

    static class BlockingProcessor implements Processor {

        @Override
        public void process(Exchange exchange) throws Exception {
            TimeUnit.SECONDS.sleep(5);
        }

    }

    static class MyWireTapRouteBuilder extends RouteBuilder {

        @Override
        public void configure() throws Exception {
            from("direct:start").
            wireTap("direct:mywiretap").
            process(new LogginProcessor("direct"));

            from("direct:mywiretap").
            process(new BlockingProcessor()).
            process(new LogginProcessor("wiretap"));
        }

    }

    @Produce(uri = "direct:start")
    protected ProducerTemplate producerTemplate;

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new MyWireTapRouteBuilder();
    }

    @Test
    public void shouldLogWireTapMessage() { 
        // given
        LOGGER.info("Thread={}", Thread.currentThread());

        Exchange exchange = new DefaultExchange(new DefaultCamelContext());
        // when
        producerTemplate.send(exchange);
        // then
    }

}
