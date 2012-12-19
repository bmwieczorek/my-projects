package com.bawi.camel;

import java.util.List;

public class B implements BIface {
    
    private final C c;

    public B(C c) {
        this.c = c;
    }

    @Override
    public String addMinuses(String text) {
        return text + "b" + c.getValue();
    }
    
    @Override
    public String addMinuses(List<String> words) {
        String result = null;
        for (String word : words) {
            result += result + word + "b";
        }
        return result;
    }

}
