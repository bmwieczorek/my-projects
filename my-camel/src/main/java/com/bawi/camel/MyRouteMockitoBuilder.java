package com.bawi.camel;

import java.util.concurrent.TimeUnit;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.ShutdownStrategy;

public class MyRouteMockitoBuilder extends RouteBuilder {

    public static final String FROM = "direct:input";
    public static final String BEAN_A = "bean:a";
    public static final String BEAN_B = "bean:b";

    @Override
    public void configure() throws Exception {

        //@formatter:off
        from(FROM)
            .to(BEAN_A)
            .to(BEAN_B);
        
        //@formatter:on

        ShutdownStrategy shutdownStrategy = getContext().getShutdownStrategy();
        shutdownStrategy.setTimeout(1);
        shutdownStrategy.setTimeUnit(TimeUnit.MINUTES);
    }

}