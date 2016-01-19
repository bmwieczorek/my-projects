package com.bawi.drools.order;

import com.bawi.drools.order.actions.CountryAndQuantityDiscountAction;
import com.bawi.drools.order.domain.Address;
import com.bawi.drools.order.domain.OrderRQ;
import com.bawi.drools.order.domain.Product;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.*;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class MyOrderDroolsTest {

    private StatefulKnowledgeSession knowledgeSession;
    private KnowledgeRuntimeLogger logger;

    @Before
    public void setup() {
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("order-rules.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = knowledgeBuilder.getErrors();
        if (errors.size() > 0) {
            errors.forEach(System.err::println);
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
        knowledgeSession = knowledgeBase.newStatefulKnowledgeSession();
        logger = KnowledgeRuntimeLoggerFactory.newFileLogger(knowledgeSession, "target/orders-test"); // logs to target/orders-test.log
    }

    @Test
    public void shouldApplyDiscount() {
        // given
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

        CountryAndQuantityDiscountAction countryAndQuantityDiscountAction = new CountryAndQuantityDiscountAction();

        // when
        knowledgeSession.insert(orderRQ);
        knowledgeSession.insert(immunePunch);
        knowledgeSession.insert(vitC);
        knowledgeSession.insert(address);
        knowledgeSession.setGlobal("countryAndQuantityDiscountAction", countryAndQuantityDiscountAction);
        knowledgeSession.fireAllRules(); // defined in order-rules.drl

        BigDecimal actual = getTotalOrderPrice(knowledgeSession);
        BigDecimal expected = new BigDecimal(4 * (10 * 0.1) + 35);

        // then
        Assert.assertEquals(0, expected.compareTo(actual));

    }

    private BigDecimal getTotalOrderPrice(StatefulKnowledgeSession knowledgeSession) {
        knowledgeSession.getObjects()
                .stream()
                .forEach(o -> System.out.println("Session object: " + o));

        return knowledgeSession.getObjects()
                .stream()
                .filter(o -> o instanceof OrderRQ)
                .map(o -> (OrderRQ) o)
                .findFirst()
                .get()
                .calculateTotalValue();
    }

    @After
    public void cleanUp() {
        logger.close();
    }

}