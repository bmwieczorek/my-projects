package com.bawi.camel;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

public class ExchangeInspector {

    private final CamelContext context;
    private final List<Exchange> exchangeHolder = new ArrayList<Exchange>();

    public ExchangeInspector(CamelContext context) {
        this.context = context;
    }

    public void registerSentToEndpointInterceptor(final String uri) throws Exception {
        RouteDefinition routeDefinition = context.getRouteDefinitions().get(0);
        RouteBuilder builder = new RouteBuilder() {

            @Override
            public void configure() throws Exception {

                interceptSendToEndpoint(uri).process(new Processor() {

                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchangeHolder.add(0, exchange);
                    }
                });
            }
        };
        routeDefinition.adviceWith(context, builder);

    }

    public Exchange getExchange() {
        return exchangeHolder.get(0);
    }

}
