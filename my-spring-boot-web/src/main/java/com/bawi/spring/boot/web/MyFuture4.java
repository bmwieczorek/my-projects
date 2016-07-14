package com.bawi.spring.boot.web;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MyFuture4 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> intCF = new CompletableFuture<>();
        //intCF.complete(42);
        Thread.sleep(1000);
        System.out.println(intCF.get());

    }
}
