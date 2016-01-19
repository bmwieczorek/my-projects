package com.bawi.drools.order;

import java.math.BigDecimal;

public class CountryDiscountAction {

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
