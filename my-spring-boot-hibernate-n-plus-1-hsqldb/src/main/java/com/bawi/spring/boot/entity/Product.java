package com.bawi.spring.boot.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product extends BaseEntity {

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "shop_id", foreignKey = @ForeignKey(name = "fk_product_shop"))
    private Shop shop;

    Product() {
        // default constructor needed for ORM
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    void addShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "Product{" +
                super.toString() +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", shop.id=" + shop.getId() + '}';
    }
}
