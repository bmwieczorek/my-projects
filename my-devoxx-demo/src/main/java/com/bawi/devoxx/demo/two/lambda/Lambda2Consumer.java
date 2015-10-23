package com.bawi.devoxx.demo.two.lambda;

public class Lambda2Consumer {

    interface Callback<T> { // Consumer
        public void accept(T t);
    }

    static class Lazy<T> {
        private Callback<T> callback;

        public Lazy(Callback<T> callback) {
            this.callback = callback;
        }

        public void accept(T t) {
            callback.accept(t);
        }
    }

    public static void main(String[] args) {
        // 1. via anonymous inner class
        Lazy<String> lazy = new Lazy<>(new Callback<String>() {
            
            @Override
            public void accept(String t) {
                System.out.println(t);
            }
        });
        System.out.println("before lazy accept");
        lazy.accept("abc123");
        System.out.println("after lazy accept");

        // 2. lambda
        Lazy<String> lazy2 = new Lazy<>(t -> System.out.println(t));
        lazy2.accept("abc123");

        // 3. block
        Lazy<String> lazy3 = new Lazy<>(t -> {
            System.out.println(t);
        });
        lazy3.accept("abc123");

        // 4. (static) method reference
        Lazy<String> lazy4 = new Lazy<>(System.out::println);
        lazy4.accept("abc123");

    }
}
