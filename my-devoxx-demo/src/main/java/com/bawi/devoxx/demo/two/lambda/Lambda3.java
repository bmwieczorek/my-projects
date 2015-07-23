package com.bawi.devoxx.demo.two.lambda;

public class Lambda3 {

    interface Callback<R> { // Supplier
        R get();
    }

    static class Lazy<R> {
        private Callback<R> callback;

        public Lazy(Callback<R> callback) {
            this.callback = callback;
        }

        public R get() {
            return callback.get();
        }

    }

    public static void main(String[] args) {

        System.out.println("123");

        Callback<Integer> get123Callback = new Callback<Integer>() {

            @Override
            public Integer get() {
                return 123;
            }
        };
        Lazy<Integer> lazy = new Lazy<Integer>(get123Callback);
        System.out.println(lazy.get());

        Lazy<Integer> lazy2 = new Lazy<Integer>(() -> 123);
        System.out.println(lazy2.get());

    }

}
