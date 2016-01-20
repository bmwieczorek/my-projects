package com.bawi.drools.order.domain;

import java.util.List;

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

    public boolean isCountryInListCaseNotSensitive(List<String> countries) {
        return countries
                    .stream()
                    .filter(c -> c.equalsIgnoreCase(country))
                    .findFirst()
                    .isPresent();
    }
}
