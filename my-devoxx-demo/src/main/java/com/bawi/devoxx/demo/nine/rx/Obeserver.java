package com.bawi.devoxx.demo.nine.rx;

import java.util.function.Consumer;

@SuppressWarnings("unused")
public class Obeserver {

    // NOT FINISHED

    public static class Observable<T> {

        private Consumer<Subscriber<? super T>> consumer;

        public Observable(Consumer<Subscriber<? super T>> consumer) {
            this.consumer = consumer;
        }

        public Subscription subscribe(Consumer<? super T> consumer) {
            return null;
        }
    }

    static class Subscriber<T> implements Observerr<T>, Subscription {
        private Observable<T> observable;

        public Subscriber(Observable<T> observable) {
            this.observable = observable;
        }

        @Override
        public void onNext(T t) {

        }
    }

    interface Observerr<T> {
        void onNext(T t);
    }

    interface Subscription {

    }

    public static class Info {

        private int i;

        public Info(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "Info [i=" + i + "]";
        }

    }

    public static class Server {

        public static Observable<Info> getFeed(String text) {
            return new Observable<Info>(subscriber -> {
                Info info = new Info(1);
                subscriber.onNext(info);
            });
        }

    }

    public static void main(String[] args) {
        Observable<Info> feed = Server.getFeed("abc");
        feed.subscribe(System.out::println);
    }
}
