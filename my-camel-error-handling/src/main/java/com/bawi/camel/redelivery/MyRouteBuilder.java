package com.bawi.camel.redelivery;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
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
            .log(LoggingLevel.WARN, "com.bawi.camel.redelivery.MyRouteBuilder","in on GLOBAL ON EXCEPTION Throwable")
            .setBody(constant(GLOBAL_ERROR_HANDLER_ERROR_MESSAGE));


        from(MY_DIRECT_START)
            .onException(IllegalArgumentException.class)
                .maximumRedeliveries(2)
                .onRedelivery(myOnRedeliveryProcessor)
                .handled(true)
                .log(LoggingLevel.INFO, "com.bawi.camel.redelivery.MyRouteBuilder","in main route ON EXCEPTION IllegalArgumentException")
                .process(new Processor() {
                    
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String body = exchange.getIn().getBody(String.class);
                        LOGGER.info("body={}", body);
                        if (body.contains("error")) {
                            throw new IllegalArgumentException("Max retry limit exceeded");
                        }
                    }
                })
            .end()
            .log(LoggingLevel.INFO, "com.bawi.camel.redelivery.MyRouteBuilder","in main route before subroute")
            .to("direct:subroute")
            .log(LoggingLevel.INFO, "com.bawi.camel.redelivery.MyRouteBuilder","in main - end of processing");

        from("direct:subroute")
            .errorHandler(noErrorHandler())
            .log(LoggingLevel.INFO, "com.bawi.camel.redelivery.MyRouteBuilder","in subroute before calling MySubSystemProcessor, body=${body}")
            .to("bean:mySubSystemProcessor")
            .log(LoggingLevel.INFO, "com.bawi.camel.redelivery.MyRouteBuilder","in subroute - end of processing");
    }

    public void setMyOnRedeliveryProcessor(MyOnRedeliveryProcessor myOnRedeliveryProcessor) {
        this.myOnRedeliveryProcessor = myOnRedeliveryProcessor;
    }
}
