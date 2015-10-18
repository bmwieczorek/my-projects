package com.bawi.atomic;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {
    public static void main(String[] args) {
        long andAdd = new AtomicLong().get();
        System.out.println(andAdd);
        long andAdd1 = new AtomicLong().incrementAndGet();
        System.out.println(andAdd1);
        long andAdd2 = new AtomicLong().addAndGet(2L);
        System.out.println(andAdd2);
        long andAdd3 = new AtomicLong(3L).updateAndGet(n -> n * n);
        System.out.println(andAdd3);
        long andAdd4 = new AtomicLong(3L).accumulateAndGet(2, (n,x) -> n * x);
        System.out.println(andAdd4);
    }

}
