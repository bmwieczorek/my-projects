package com.bawi.drools.order;

import com.bawi.drools.RoolVO;
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
    private KnowledgeBase kbase;

    @Before
    public void setup() {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("orders.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());


    }

    @Test
    public void testBasic() {

        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "target/orders-test"); // logs to target/orders-test.log

        OrderRQ orderRQ = new OrderRQ();

        Product vitC = new Product();
        vitC.setId(1L);
        vitC.setName("Vit C");
        vitC.setPrice(new BigDecimal("12.34"));
        vitC.setQuantity(4);

        Product immunePunch = new Product();
        immunePunch.setId(1L);
        immunePunch.setName("Immune Punch");
        immunePunch.setPrice(new BigDecimal("37.89"));
        immunePunch.setQuantity(1);

        orderRQ.setProducts(Arrays.asList(vitC, immunePunch));
        Address address = new Address();
        address.setCountry("Poland");
        orderRQ.setAddress(address);

        ksession.insert(orderRQ);
        ksession.insert(immunePunch);
        ksession.insert(vitC);
        ksession.insert(address);

        ksession.insert(new CountryDiscountAction());

        ksession.fireAllRules();
        ksession.getObjects()
                .stream()
                .filter(o -> o instanceof RoolVO)
                .forEach(o -> Assert.assertEquals("Done.", ((RoolVO) o).getStringValue()));
        logger.close();

    }
}