package com.bawi.spring4.annotation.config;


public class MyService {
    private Greeting greeting;
    public MyService(Greeting greeting) {
        this.greeting = greeting;
    }
    public void greet(String name) {
        System.out.println(greeting.getGreeting(name));
    }
}