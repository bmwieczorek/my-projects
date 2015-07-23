package com.bawi.devoxx.demo.two.lambda;

public class Lambda4 {

    interface Callback<R, T> { // Function
        R apply(T t);
    }

    static class Lazy<R, T> {
        private Callback<R, T> callback;

        public Lazy(Callback<R, T> callback) {
            this.callback = callback;
        }

        public R apply(T t) {
            return callback.apply(t);
        }
    }

    public static void main(String[] args) {

        String text = "abc";
        System.out.println(text + " " + getTextLength(text));

        Callback<Integer, String> textLengthCallback = new Callback<Integer, String>() {

            @Override
            public Integer apply(String t) {
                return getTextLength(text);
            }
        };
        Lazy<Integer, String> lazy = new Lazy<Integer, String>(textLengthCallback);
        System.out.println(text + " " + lazy.apply("abc"));

        Lazy<Integer, String> lazy2 = new Lazy<>(t -> t.length());
        // Lazy<Integer, String> lazy2 = new Lazy<>(t -> getTextLength(t));
        // Lazy<Integer, String> lazy2 = new Lazy<>(t -> {
        // int length = getTextLength(t);
        // return length;
        // });
        // Lazy<Integer, String> lazy2 = new Lazy<>(Lambda4::getTextLength);

        System.out.println(text + " " + lazy2.apply("abc"));

        Lazy<String, String> lazy3 = new Lazy<>(t -> t.toUpperCase());
        System.out.println(text + " " + lazy3.apply("abc"));
    }

    private static int getTextLength(String text) {
        return text.length();
    }
}
