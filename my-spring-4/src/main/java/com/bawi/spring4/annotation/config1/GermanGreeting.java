package com.bawi.spring4.annotation.config1;

import com.bawi.spring4.Greeting;

public class GermanGreeting implements Greeting {

    @Override
    public String getGreeting(String name) {
        return "Guten morgen " + name + "!";
    }

}