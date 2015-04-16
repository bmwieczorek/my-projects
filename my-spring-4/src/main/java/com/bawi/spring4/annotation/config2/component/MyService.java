package com.bawi.spring4.annotation.config2.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bawi.spring4.Greeting;

@Component
public class MyService {

    private Greeting greeting;

    @Autowired
    public MyService(Greeting greeting) {
        this.greeting = greeting;
    }

    public void greet(String name) {
        System.out.println(greeting.getGreeting(name));
    }

}