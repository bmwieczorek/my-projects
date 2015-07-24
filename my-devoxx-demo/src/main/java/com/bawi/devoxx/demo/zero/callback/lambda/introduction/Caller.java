package com.bawi.devoxx.demo.zero.callback.lambda.introduction;
import java.util.function.Consumer;

public class Caller {

    public static class Helper<T> {
        
        private Consumer<T> consumer;

        public Helper(Consumer<T> printable) {
            this.consumer = printable;
        }

        public void consume(T t) {
            consumer.accept(t);
        }

    }

    public static void main(String[] args) {
        String text = "Bob";
        
//        new Helper().printEn(text);
//        new Helper().printPl(text);

        Consumer<String> en = t -> System.out.println("Good morning " + text);  
        Helper<String> helper1 = new Helper<String>(en);
        helper1.consume(text);
        
        Helper<String> helper2 = new Helper<String>(t -> System.out.println("Dzien dobry " + t));
        helper2.consume(text);

    }

}
