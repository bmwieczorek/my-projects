package com.bawi.camel.timeout.processor;

import org.apache.camel.builder.RouteBuilder;


public class SampleRoute extends RouteBuilder {

    public static final String FROM_URI = "direct:TEST_FROM_URI";

    @Override
    public void configure() throws Exception {

        // @formatter:off
        from(FROM_URI)
            .to("bean:businessObjectCreatorStub")
            .to("bean:storeInDBWithTimeoutProcessor");
        // @formatter:on
    }

}
