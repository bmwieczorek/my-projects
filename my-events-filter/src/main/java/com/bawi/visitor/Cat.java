package com.bawi.visitor;

public class Cat {

    private final String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cat [name=" + name + "]";
    }

}
