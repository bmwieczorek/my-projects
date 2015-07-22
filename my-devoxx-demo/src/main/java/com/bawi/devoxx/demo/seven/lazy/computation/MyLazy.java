package com.bawi.devoxx.demo.seven.lazy.computation;

import java.util.function.Supplier;

public class MyLazy {

    static class Lazy<T> {

        private Supplier<T> supplier;

        public Lazy(Supplier<T> supplier) {
            this.supplier = supplier;

        }

        public T get() {
            return supplier.get();
        }

    }

    public static void main(String[] args) {
        int n = 10;

        // int value = square(n);
        // System.out.println("Before printing value");
        // System.out.println(value);

        // vs

        Lazy<Integer> lazy = new Lazy<Integer>(() -> square(n));

        // same as

        // Lazy<Integer> lazy = new Lazy<Integer>(new Supplier<Integer>() {
        // @Override
        // public Integer get() {
        // return square(n);
        // }
        // });
        System.out.println("Before printing value");
        System.out.println(lazy.get());

    }

    public static int square(int n) {
        System.out.println("Computing square for n=" + n);
        return n * n;
    }
}
