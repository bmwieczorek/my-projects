package com.bawi.camel;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFileSplitRouteBuilder extends RouteBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyCamelStandalone.class);
    private static final String SPLIT_TOKEN = "UNH";
    private final String TOTAL_COUNT = "TOTAL_COUNT";
    private final String MIN_LENGTH_BODY = "MIN_LENGTH_BODY";
    private final String MAX_LENGTH_BODY = "MAX_LENGTH_BODY";

    @Override
    public void configure() throws Exception {
        AggregationStrategy aggregationStrategy = new AggregationStrategy() {
            
            @Override
            public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
                String newBody = newExchange.getIn().getBody(String.class);
                int newBodyLength = newBody.length();
                if (oldExchange == null) {
                    newExchange.setProperty(TOTAL_COUNT, 1);
                    newExchange.setProperty(MIN_LENGTH_BODY, newBody);
                    newExchange.setProperty(MAX_LENGTH_BODY, newBody);
                    return newExchange;
                }
                
                Integer totalCount = oldExchange.getProperty(TOTAL_COUNT, Integer.class);
                String minLengthBody = oldExchange.getProperty(MIN_LENGTH_BODY, String.class);
                String maxLengthBody = oldExchange.getProperty(MAX_LENGTH_BODY, String.class);


                oldExchange.setProperty(TOTAL_COUNT, ++totalCount);
                oldExchange.setProperty(MIN_LENGTH_BODY, newBodyLength < minLengthBody.length() ? newBody : minLengthBody);
                oldExchange.setProperty(MAX_LENGTH_BODY,  newBodyLength > maxLengthBody.length() ? newBody : maxLengthBody);
                return oldExchange;
            }
        };
        
        from("file:inbox").routeId("myRoute")
            .split(body().tokenize(SPLIT_TOKEN),aggregationStrategy)
                .streaming()
                .process(new Processor() {
                    
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String body = SPLIT_TOKEN + exchange.getIn().getBody(String.class);
                        exchange.getIn().setBody(body);
                        logTwoDifferentEmdNumbersIfPresent(body);
                    }

                    
                })
            .end()
            .process(new Processor() {
                
                @Override
                public void process(Exchange exchange) throws Exception {
                    LOGGER.info("Finished processing with total count: {}\nmin length {} chars: \n{}\nmax length {} chars: \n{}", new Object[] {
                            exchange.getProperty(TOTAL_COUNT),
                            exchange.getProperty(MIN_LENGTH_BODY, String.class).length(),
                            exchange.getProperty(MIN_LENGTH_BODY),
                            exchange.getProperty(MAX_LENGTH_BODY, String.class).length(),
                            exchange.getProperty(MAX_LENGTH_BODY)});                
                    }
            });
    }
    
    private void logTwoDifferentEmdNumbersIfPresent(String body) {
        Set<String> numbers = new LinkedHashSet<String>();
        Pattern p = Pattern.compile("TKT\\+[0-9]{13}");
        Matcher matcher = p.matcher(body);
        while(matcher.find()) {
            numbers.add(matcher.group());
        }
        if (numbers.size() > 1) {
            LOGGER.info("Found different EMD numbers in one edifact {}", numbers);
        }
    }
}