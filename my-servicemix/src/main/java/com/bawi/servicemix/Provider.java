package com.bawi.servicemix;

public class Provider {

    private String name;

    public Provider() {
        System.out.println("Provider created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
