package com.bawi.devoxx.demo.two.lambda;

public class Lambda2 {

    interface Callback<T> { // Predicate
        boolean matches(T t); // test
    }

    static class Lazy<T> {
        private Callback<T> callback;

        public Lazy(Callback<T> callback) {
            this.callback = callback;
        }

        public boolean matches(T t) {
            return callback.matches(t);
        }

    }

    public static void main(String[] args) {

        System.out.println(isEvenNumber(2));

        Callback<Integer> isEvenNumberCallback = new Callback<Integer>() {

            @Override
            public boolean matches(Integer n) {
                return isEvenNumber(n);
            }

        };

        Lazy<Integer> lazy = new Lazy<Integer>(isEvenNumberCallback);
        System.out.println(lazy.matches(2));

        Lazy<Integer> lazy2 = new Lazy<Integer>(n -> isEvenNumber(n));
        // Lazy<Integer> lazy2 = new Lazy<Integer>(Lambda2::isEvenNumber);
        System.out.println(lazy2.matches(2));

        Lazy<Integer> lazy3 = new Lazy<Integer>(n -> n % 2 == 1);
        System.out.println(lazy3.matches(2));

    }

    private static boolean isEvenNumber(int n) {
        return n % 2 == 0;
    }

}
