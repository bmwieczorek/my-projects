package com.bawi.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder{
    static final String MY_DIRECT_START = "direct:start";

    private Processor myRedeliveryProcessor;

    @Override
    public void configure() throws Exception {
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
            .log(LoggingLevel.INFO, "com.bawi.camel.MyRouteBuilder","in subroute before calling sub system")
            .to("bean:mySubSystemProcessor")
            .log(LoggingLevel.INFO, "com.bawi.camel.MyRouteBuilder","in subroute after calling sub system");
    }

    public void setMyRedeliveryProcessor(Processor myRedeliveryProcessor) {
        this.myRedeliveryProcessor = myRedeliveryProcessor;
    }
}
