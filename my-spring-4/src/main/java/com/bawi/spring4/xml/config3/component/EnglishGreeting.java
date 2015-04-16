package com.bawi.spring4.xml.config3.component;

import org.springframework.stereotype.Component;

import com.bawi.spring4.Greeting;

@Component("EnglishGreeting")
public class EnglishGreeting implements Greeting {

    @Override
    public String getGreeting(String name) {
        return "Good morning " + name + "!";
    }

}