package com.bawi.devoxx.demo.seven.lazy.computation;

public class MyLazy2 {
    interface MySupplier<T> {
        T get();
    }

    static class Lazy2<T> {

        private MySupplier<T> mySupplier;

        public Lazy2(MySupplier<T> mySupplier) {
            this.mySupplier = mySupplier;

        }

        public T get() {
            return mySupplier.get();
        }

    }

    public static void main(String[] args) { // it does not need to be java java.util.function.Supplier
        int n = 10;

        // int value = square(n);
        // System.out.println("Before printing value");
        // System.out.println(value);

        // vs

        Lazy2<Integer> lazy2 = new Lazy2<Integer>(() -> square(n));

        // same as

        // Lazy2<Integer> lazy2 = new Lazy2<Integer>(new MySupplier<Integer>() {
        // @Override
        // public Integer get() {
        // return square(n);
        // }
        // });
        System.out.println("Before printing value");
        System.out.println(lazy2.get());

    }

    public static int square(int n) {
        System.out.println("Computing square for n=" + n);
        return n * n;
    }
}
