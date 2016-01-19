package com.bawi.drools.order;

import java.math.BigDecimal;
import java.util.List;

public class OrderRQ {
    private List<Product> products;
    private Address address;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BigDecimal calculateTotalValue() {
        return products.stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "OrderRQ{" +
                "products=" + products +
                ", address=" + address +
                '}';
    }
}
