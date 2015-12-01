package com.bawi.camel.redelivery;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRouteBuilder extends RouteBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRouteBuilder.class);
    static final String GLOBAL_ERROR_HANDLER_ERROR_MESSAGE = "My error";
    static final String MY_DIRECT_START = "direct:start";

    private MyOnRedeliveryProcessor myOnRedeliveryProcessor;

    @Override
    public void configure() throws Exception {

        errorHandler(defaultErrorHandler().logStackTrace(false)); // keep existing default error handler but do not log stack trace

        onException(Throwable.class)
            .handled(true)
            .log(LoggingLevel.WARN, MyRouteBuilder.class.getName(), "in on GLOBAL ON EXCEPTION Throwable")
            .setBody(constant(GLOBAL_ERROR_HANDLER_ERROR_MESSAGE));


        from(MY_DIRECT_START)
            .onException(IllegalArgumentException.class)
                .maximumRedeliveries(2)
                .redeliveryDelay(100)
                .onRedelivery(myOnRedeliveryProcessor)
                .handled(true)
                .log(LoggingLevel.INFO,  MyRouteBuilder.class.getName(), "in main route ON EXCEPTION IllegalArgumentException")
                .process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);
                    LOGGER.info("body={}", body);
                    if (body.contains("error")) {
                        throw new IllegalArgumentException("Max retry limit exceeded");
                    }
                })
            .end()
            .log(LoggingLevel.INFO, MyRouteBuilder.class.getName(), "in main route before subroute")
            .to("direct:subroute")
            .log(LoggingLevel.INFO, MyRouteBuilder.class.getName(), "in main - end of processing");

        from("direct:subroute")
            .errorHandler(noErrorHandler()) // disable error handler, so the entire route can be retried in case of redelivery
            .log(LoggingLevel.INFO, MyRouteBuilder.class.getName(), "in subroute before calling MySubSystemProcessor, body=${body}")
            .to("bean:mySubSystemProcessor")
            .log(LoggingLevel.INFO, MyRouteBuilder.class.getName(), "in subroute - end of processing");
    }

    public void setMyOnRedeliveryProcessor(MyOnRedeliveryProcessor myOnRedeliveryProcessor) {
        this.myOnRedeliveryProcessor = myOnRedeliveryProcessor;
    }
}
