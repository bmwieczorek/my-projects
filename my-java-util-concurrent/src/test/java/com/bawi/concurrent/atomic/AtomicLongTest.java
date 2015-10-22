package com.bawi.concurrent.atomic;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

public class AtomicLongTest {
    
    @Test
    public void shouldaction() {
        assertEquals(0, new AtomicLong().get());
        assertEquals(1, new AtomicLong().incrementAndGet());
        assertEquals(2, new AtomicLong().addAndGet(2L));
        assertEquals(9, new AtomicLong(3L).updateAndGet(n -> n * n));
        assertEquals(6, new AtomicLong(3L).accumulateAndGet(2, (n,x) -> n * x));

        AtomicLong atomicLong = new AtomicLong();
        atomicLong.set(1);
        assertEquals(1, atomicLong.get());
    }
}
