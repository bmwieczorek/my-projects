package com.bawi.java8.stats;

import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

import org.junit.Test;

import com.bawi.java8.stats.Stats;
import com.bawi.java8.stats.StatsCollector;

public class StatsTest {

    @Test
    public void testStats() throws Exception {
        Stats stats = Stream.of(0.5,1.0).
            collect(new StatsCollector());

        assertEquals(0.5d, stats.getMin(), 0.0d);
        assertEquals(1.0d, stats.getMax(), 0.0d);
        assertEquals(0.75d, stats.getAvg(), 0.0d);
    }
}
