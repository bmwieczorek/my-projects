package com.bawi.spring.boot.web;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartosz Wieczorek on 7/5/2016.
 */
public class MyList {
    public static void main(String[] args) {
        List<String> my = new ArrayList<>();
        my.add("a");
        my.add("b");
        my.add("c");
        my.add(0, "z");
        my.add(2, "y");
        System.out.println(my);
    }
}
