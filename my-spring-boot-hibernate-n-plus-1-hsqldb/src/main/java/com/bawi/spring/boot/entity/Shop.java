package com.bawi.spring.boot.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Shop extends BaseEntity {

    @Column(name = "shipment_address", nullable = false)
    private String shipmentAddress;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    Shop() {
        // default constructor needed for ORM
    }

    public Shop(String shipmentAddress, Product... products) {
        this.shipmentAddress = shipmentAddress;
        for (Product product : products) {
            product.addShop(this);
            this.products.add(product);
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Shop{" +
                super.toString() +
                ", shipmentAddress='" + shipmentAddress + '\'' +
                //", products=" + products +
                '}';
    }
}
