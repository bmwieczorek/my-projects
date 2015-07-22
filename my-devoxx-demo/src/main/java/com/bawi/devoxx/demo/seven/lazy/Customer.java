package com.bawi.devoxx.demo.seven.lazy;

public class Customer {

    private String name;
    private int id;
    private Double balance;

    public Customer(String name) {
        this.name = name;
    }

    public Customer(String name, int id, Double balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Customer [name=" + name + ", id=" + id + ", balance=" + balance + "]";
    }

}
