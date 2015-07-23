package com.bawi.devoxx.demo.two.lambda;

public class Lambda1 {

    interface Callback { // Consumer
        public void accept();
    }

    static class Lazy {
        private Callback callback;

        public Lazy(Callback callback) {
            this.callback = callback;
        }

        public void accept() {
            callback.accept();
        }

    }

    public static void main(String[] args) {

        System.out.println("hello");

        Callback printHelloCallback = new Callback() {

            @Override
            public void accept() {
                System.out.println("Hello");
            }
        };

        Lazy lazy = new Lazy(printHelloCallback);
        lazy.accept();

        Lazy lazy2 = new Lazy(() -> System.out.println("Hello"));
        lazy2.accept();

    }

}
