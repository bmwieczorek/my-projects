package com.bawi.spring4.xml.config3.component;

import org.springframework.stereotype.Component;

import com.bawi.spring4.Greeting;

@Component("GermanGreeting")
public class GermanGreeting implements Greeting {

    @Override
    public String getGreeting(String name) {
        return "Guten morgen " + name + "!";
    }

}