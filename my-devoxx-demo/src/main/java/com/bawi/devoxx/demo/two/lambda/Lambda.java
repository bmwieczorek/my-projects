package com.bawi.devoxx.demo.two.lambda;

public class Lambda {

    public static void main(String[] args) {
        String name = "Bob";
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("Hello " + name);

            }
        }).start();

        new Thread(() -> System.out.println("Hello " + name)).start(); // lambda is like a anonymous function
    }

}
