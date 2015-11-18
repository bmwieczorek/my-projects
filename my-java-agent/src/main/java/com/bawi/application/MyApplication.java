package com.bawi.application;

public class MyApplication {
    public static void main(String[] args) {
        new MyApplication().getHelloMessage("Foo");
    }

    public void getHelloMessage(String name) {
        System.out.println("Hello " + name);
    }
}
