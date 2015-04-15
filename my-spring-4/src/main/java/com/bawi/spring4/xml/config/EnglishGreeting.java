package com.bawi.spring4.xml.config;

import org.springframework.stereotype.Component;

@Component
public class EnglishGreeting implements Greeting {
    @Override
    public String getGreeting(String name) {
        return "Good morning " + name + "!";
    }
}