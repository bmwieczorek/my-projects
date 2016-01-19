package com.bawi.drools.order;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.*;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class MyOrdersDroolsTest {
    private KnowledgeBase knowledgeBase;

    @Before
    public void setup() {
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("orders.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = knowledgeBuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());


    }

    @Test
    public void testBasic() {

        StatefulKnowledgeSession ksession = knowledgeBase.newStatefulKnowledgeSession();
        KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "target/orders-test"); // logs to target/orders-test.log

        OrderRQ orderRQ = new OrderRQ();

        Product vitC = new Product();
        vitC.setId(1L);
        vitC.setName("Vit C");
        vitC.setPrice(new BigDecimal("10.00"));
        vitC.setQuantity(4);

        Product immunePunch = new Product();
        immunePunch.setId(2L);
        immunePunch.setName("Immune Punch");
        immunePunch.setPrice(new BigDecimal("35.00"));
        immunePunch.setQuantity(1);

        orderRQ.setProducts(Arrays.asList(vitC, immunePunch));
        Address address = new Address();
        address.setCountry("Poland");
        orderRQ.setAddress(address);

        ksession.insert(orderRQ);
        ksession.insert(immunePunch);
        ksession.insert(vitC);
        ksession.insert(address);

        CountryDiscountAction countryDiscountAction = new CountryDiscountAction();
        ksession.setGlobal("countryDiscountAction", countryDiscountAction);

        ksession.fireAllRules();

        ksession.getObjects()
                .stream()
                .forEach(o -> System.out.println("Session object: " + o));

        BigDecimal actual = ksession.getObjects()
                .stream()
                .filter(o -> o instanceof OrderRQ)
                .map(o -> (OrderRQ) o)
                .findFirst()
                .get()
                .calculateTotalValue();

        BigDecimal expected = new BigDecimal(4 * (10 * 0.1) + 35);
        Assert.assertEquals(0, expected.compareTo(actual));

        logger.close();

    }
}