package com.bawi.spring4.annotation.config1;

import com.bawi.spring4.Greeting;

public class EnglishGreeting implements Greeting {

    @Override
    public String getGreeting(String name) {
        return "Good morning " + name + "!";
    }

}