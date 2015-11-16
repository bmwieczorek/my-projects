package com.bawi.camel.onexception;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {
    static final String MY_DIRECT_START = "direct:start";

    @Override
    public void configure() throws Exception {
        
        errorHandler(defaultErrorHandler().logStackTrace(false)); // keep existing default error handler but do not log stack trace
//        errorHandler(noErrorHandler()); 

//        onException(IllegalArgumentException.class)
//            .handled(true) // throw exception to the client (false) or return the exchange (true) 
//            .log(LoggingLevel.INFO, "com.bawi.camel.onexception.MyRouteBuilder","in onException route")
//            .to("bean:mySubsystemExceptionHandlerProcessor")
//            .end();

        from(MY_DIRECT_START)
            .onException(IllegalArgumentException.class)
                .handled(true) // throw exception to the client (false) or return the exchange (true) 
                .log(LoggingLevel.INFO, "com.bawi.camel.onexception.MyRouteBuilder","in onException route")
                .to("bean:mySubsystemExceptionHandlerProcessor")
            .end()
            .to("bean:mySubSystemProcessor");
    }
}
