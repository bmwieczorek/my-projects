package com.bawi.drools.order.domain;

public class Address {
    private String country;

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
