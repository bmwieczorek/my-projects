package com.bawi.spring.boot.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by Bartosz Wieczorek on 6/23/2016.
 */
public class MyCompletableCompose {
    public static void main(String[] args) {
        CompletableFuture<List<String>> lscf = new CompletableFuture<>();
        lscf.complete(Arrays.asList("a", "b", "c"));

        CompletableFuture<List<String>> lscf2 = new CompletableFuture<>();
        lscf2.complete(Arrays.asList("A", "B", "C"));

        CompletableFuture<List<String>> listCompletableFuture = lscf.thenCombineAsync(lscf2, (List<String> list1, List<String> list2) -> {
            ArrayList<String> strings = new ArrayList<>();
            strings.addAll(list1);
            strings.addAll(list2);
            return strings;
        });


        System.out.println("result" + listCompletableFuture.join());


    }
}
