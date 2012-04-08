package com.bawi.eventsfilter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bawi.eventsfilter.impl.TimeExpiringEventsStorage;

public class TimeExpiringEventsStorageTest {

    private final Time time = new Time();
    private final TimeExpiringEventsStorage storage = new TimeExpiringEventsStorage(time);

    @Test
    public void shouldExpireEventAfterTtlExceeded() {
        // given
        Event event1 = createEventWithId(1);
        storage.addEvent(event1);
        Event event2 = createEventWithId(2);
        storage.addEvent(event2);

        // when
        time.addTimeMillis(2 * TimeExpiringEventsStorage.DEFAULT_TTL_MILLIS);
        Event event3 = createEventWithId(3);
        storage.addEvent(event3);

        // then
        assertEquals(1, storage.getEventsCount());
        assertEquals(event3, storage.getEvents().get(0));
    }

    private Event createEventWithId(int id) {
        return new Event(id, new Object(), time);
    }
}
