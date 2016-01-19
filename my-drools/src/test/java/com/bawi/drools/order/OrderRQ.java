package com.bawi.drools.order;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by SG0212148 on 18-Jan-16.
 */
public class OrderRQ {
    private List<Product> products;

    @Override
    public String toString() {
        return "OrderRQ{" +
                "products=" + products +
                ", address=" + address +
                ", discount=" + discount +
                ", totalValue=" + totalValue +
                '}';
    }

    private Address address;
    private BigDecimal discount;

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    private BigDecimal totalValue;

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

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
}
