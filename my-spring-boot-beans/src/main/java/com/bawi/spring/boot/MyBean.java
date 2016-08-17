package com.bawi.spring.boot;

public class MyBean {

    public MyBean(String appName) {
        System.out.println("MyBean constructor: " + appName);
    }

    void init() {
        System.out.println("MyBean init method");
    }

    void destroy() {
        System.out.println("MyBean destroy method");
    }
}
