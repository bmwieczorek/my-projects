package com.bawi.devoxx.demo.two.lambda;

public class Lambda1 {

    interface Callback {
        public void doIt();
    }

    static class Lazy {
        private Callback callback;

        public Lazy(Callback callback) {
            this.callback = callback;
        }

        public void doIt() {
            callback.doIt();
        }

    }

    public static void main(String[] args) {

        System.out.println("hello");

        Callback printHelloCallback = new Callback() {

            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };

        Lazy lazy = new Lazy(printHelloCallback);
        lazy.doIt();

        Lazy lazy2 = new Lazy(() -> System.out.println("Hello"));
        lazy2.doIt();

    }

}
