package com.bawi.springaop.service;

public class MyServiceImpl implements MyService {

    @Override
    public void myMethod() {
        System.out.println("My intefaced method");
    }

    public void myNonInterfacedMethod() {
        System.out.println("My non intefaced method");
    }

}
