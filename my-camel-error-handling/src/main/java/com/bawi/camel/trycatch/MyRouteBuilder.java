package com.bawi.camel.trycatch;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {
    private static final String ORIGINAL_RQ = "originalRq";
    static final String MY_DIRECT_START = "direct:start";

    @Override
    public void configure() throws Exception {

        onException(Throwable.class)
            .handled(true)
            .log(LoggingLevel.WARN, "com.bawi.camel.trycatch.MyRouteBuilder","in on exception throwable")
            .setBody(constant("My error"));


        from(MY_DIRECT_START)
            .log(LoggingLevel.INFO, "com.bawi.camel.trycatch.MyRouteBuilder","in main route before subroute")
            .to("direct:subroute");

        from("direct:subroute")
            .log(LoggingLevel.INFO, "com.bawi.camel.trycatch.MyRouteBuilder","in subroute before calling MySubSystemProcessor, body=${body}, header=${header.myHeader}, property=${property.myProperty} ")
            .doTry()
                .log(LoggingLevel.INFO, "com.bawi.camel.trycatch.MyRouteBuilder","in try before calling MySubSystemProcessor, body=${body}")
                .setProperty(ORIGINAL_RQ, body())
                .to("bean:mySubSystemProcessor")
                .log(LoggingLevel.INFO, "com.bawi.camel.trycatch.MyRouteBuilder","in try after calling MySubSystemProcessor, body=${body}")
            .doCatch(IllegalArgumentException.class)
                .log(LoggingLevel.INFO, "com.bawi.camel.trycatch.MyRouteBuilder","in catch before calling MySubsystemExceptionHandlerProcessor, body=${body}")
                .setBody(property(ORIGINAL_RQ))
                .to("bean:mySubSystemProcessor")
                .log(LoggingLevel.INFO, "com.bawi.camel.trycatch.MyRouteBuilder","in catch after calling sub MySubsystemExceptionHandlerProcessor, body=${body}")
             .doFinally()
                 .removeProperty(ORIGINAL_RQ)
            .end()
            .log(LoggingLevel.INFO, "com.bawi.camel.trycatch.MyRouteBuilder","in subroute - end of processing, body=${body}");
    }

}
