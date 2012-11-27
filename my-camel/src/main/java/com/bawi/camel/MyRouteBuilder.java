package com.bawi.camel;

import java.util.concurrent.TimeUnit;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.ShutdownStrategy;

public class MyRouteBuilder extends RouteBuilder {

    private Processor processor;

    @Override
    public void configure() throws Exception {

        //@formatter:off
        from("file://target/inbox")
        .process(processor)
        .to("file://target/outbox");
        //@formatter:on

        ShutdownStrategy shutdownStrategy = getContext().getShutdownStrategy();
        shutdownStrategy.setTimeout(1);
        shutdownStrategy.setTimeUnit(TimeUnit.MINUTES);
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

}