package com.bawi.camel.onexception;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class MyRouteWithSubRouteBuilder extends RouteBuilder {
    static final String MY_DIRECT_START = "direct:start-main-route";

    @Override
    public void configure() throws Exception {
       
       // error handler is need to route the exception to onException clause 
       //     otherwise exception thrown in the route will be returned to the called 
       errorHandler(defaultErrorHandler().logStackTrace(false)); // keep existing default error handler but do not log stack trace
//        errorHandler(noErrorHandler()); // used to disable error handling

//        onException(IllegalArgumentException.class)
//            .handled(true) // throw exception to the client (false) or return the exchange (true) 
//            .log(LoggingLevel.INFO, "com.bawi.camel.onexception.MyRouteBuilder","in GLOBAL onException route")
//            .to("bean:mySubsystemExceptionHandlerProcessor")
//            .end();

        from(MY_DIRECT_START)
            .onException(IllegalArgumentException.class)
                .handled(true) // throw exception to the client (false) or return the exchange (true) 
                .log(LoggingLevel.INFO, "com.bawi.camel.onexception.MyRouteWithSubRouteBuilder","in main route onException route")
                .to("bean:mySubsystemExceptionHandlerProcessor")
            .end()
            .to("direct:mySubroute");
        
            from("direct:mySubroute")
                .errorHandler(noErrorHandler())
                .to("bean:mySubSystemProcessor");
    }
}
