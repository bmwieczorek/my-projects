package com.bawi.spring4.xml.config;


//@Component
public class GermanGreeting implements Greeting {
    @Override
    public String getGreeting(String name) {
        return "Guten morgen " + name + "!";
    }
}