package com.bawi.spring4.config;

import org.springframework.stereotype.Component;

import com.bawi.spring4.Greeting;

@Component
public class EnglishGreeting implements Greeting {

    @Override
    public String getGreeting(String name) {
        return "Good morning " + name + "!";
    }

}