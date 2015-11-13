package com.bawi.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {
    static final String MY_DIRECT_START = "direct:start";

    private Processor myRedeliveryProcessor;

    @Override
    public void configure() throws Exception {

        // exception in retry will not reach this onException 
        onException(Throwable.class)
            .handled(true)
            .log(LoggingLevel.WARN, "com.bawi.camel.MyRouteBuilder","in on exception throwable")
            .setBody(body(String.class).append("My error"));


        from(MY_DIRECT_START)
            .onException(IllegalArgumentException.class)
                .maximumRedeliveries(1)
                .onRedelivery(myRedeliveryProcessor)
                .handled(false)
            .end()
            .log(LoggingLevel.INFO, "com.bawi.camel.MyRouteBuilder","in main route before subroute")
            .to("direct:subroute");

        from("direct:subroute")
            .errorHandler(noErrorHandler())
            .log(LoggingLevel.INFO, "com.bawi.camel.MyRouteBuilder","in subroute before calling MySubSystemProcessor")
            .doTry()
                .log(LoggingLevel.INFO, "com.bawi.camel.MyRouteBuilder","in try before calling MySubSystemProcessor")
                .to("bean:mySubSystemProcessor")
                .log(LoggingLevel.INFO, "com.bawi.camel.MyRouteBuilder","in try after calling MySubSystemProcessor")
            .doCatch(IllegalArgumentException.class)
                .log(LoggingLevel.INFO, "com.bawi.camel.MyRouteBuilder","in catch before calling MySubsystemExceptionHandlerProcessor")
                .to("bean:mySubsystemExceptionHandlerProcessor")
                .log(LoggingLevel.INFO, "com.bawi.camel.MyRouteBuilder","in catch after calling sub MySubsystemExceptionHandlerProcessor")
            .end()
            .log(LoggingLevel.INFO, "com.bawi.camel.MyRouteBuilder","in subroute - end of processing");
    }

    public void setMyRedeliveryProcessor(Processor myRedeliveryProcessor) {
        this.myRedeliveryProcessor = myRedeliveryProcessor;
    }
}
