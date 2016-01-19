package com.bawi.drools.order.actions;

import com.bawi.drools.order.domain.OrderRQ;
import com.bawi.drools.order.domain.Product;

import java.math.BigDecimal;

public class CountryAndQuantityDiscountAction {

    public void applyDiscount(OrderRQ orderRQ, Product product, String discountString) {
        BigDecimal discount = new BigDecimal(discountString);
        System.out.println("Applying discount of " + discount +  " to product: " + product);

        orderRQ.getProducts()
                .stream()
                .filter(p -> p.equals(product))
                .forEach(p -> p.setPrice(p.getPrice().multiply(discount)));

        System.out.println("Result (after discount): " + orderRQ);
    }
}