package com.bawi.spring4.annotation.config;

public class EnglishGreeting implements Greeting {
    @Override
    public String getGreeting(String name) {
        return "Good morning " + name + "!";
    }
}