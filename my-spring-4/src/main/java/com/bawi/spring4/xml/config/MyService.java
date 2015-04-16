package com.bawi.spring4.xml.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bawi.spring4.Greeting;

@Component
public class MyService {

    //@Qualifier("EnglishGreeting")
    @Qualifier("GermanGreeting")  //@Qualifier can only by at property or setter level
    @Autowired  // @Autowired can be at property/setter or constructor level
    private Greeting greeting; 

    public void greet(String name) {
        System.out.println(greeting.getGreeting(name));
    }

}