package com.bawi.spring4.annotation.config;

public class GermanGreeting implements Greeting {
    @Override
    public String getGreeting(String name) {
        return "Guten morgen " + name + "!";
    }
}