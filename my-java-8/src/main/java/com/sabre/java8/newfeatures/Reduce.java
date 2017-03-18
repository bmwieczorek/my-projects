package com.sabre.java8.newfeatures;

import java.util.*;
import java.util.stream.Collectors;

public class Reduce {

    static class Tuple<A, S> {
        A _1;
        S _2;
        public Tuple(A _1, S _2) {
            this._1 = _1;
            this._2 = _2;
        }

        @Override
        public String toString() {
            return "T(" + _1 + ","  + _2  + ")";
        }
    }
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a", "a", "b", "cc", "c", "c", "d", "d", "a");
        String collect = strings.stream()
                                .map(s -> new LinkedList<>(Collections.singletonList(new Tuple<>(s, 1))))
                                .reduce((ll1, ll2) -> {
                                    System.out.println(ll1 + ": " + ll2);

                                    Tuple<String, Integer> t1 = ll1.getLast();
                                    Tuple<String, Integer> t2 = ll2.getFirst();

                                    if (t1._1.equals(t2._1)) {
                                        ll1.removeLast();
                                        ll1.addLast(new Tuple<>(t1._1, t1._2 + t2._2));
                                        ll2.removeFirst();
                                    }

                                    ll1.addAll(ll2);
                                    System.out.println(ll1);
                                    return ll1;
                                }).get().stream()
                                .map(t -> t._1 + ":" + t._2)
                                .collect(Collectors.joining(","));

        System.out.println(collect);

        List<String> integers = Arrays.asList("1", "2", "3");
        long count = integers.stream().mapToInt(s -> Integer.parseInt(s)).sum();
        System.out.println(count);
    }



}

