package com.bawi.eventsfilter.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bawi.eventsfilter.Event;
import com.bawi.eventsfilter.EventsStorage;
import com.bawi.eventsfilter.Time;

public class TimeExpiringEventsStorage implements EventsStorage {

    public static final long DEFAULT_TTL_MILLIS = 1000;

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeExpiringEventsStorage.class);

    private final EventsStorage eventsStorage;

    private final Map<Class<?>, Long> objectClassToTtlMapping;

    private final Time time;

    public TimeExpiringEventsStorage() {
        this(new DefaultEventsStorage(), new Time(), new HashMap<Class<?>, Long>());
    }

    public TimeExpiringEventsStorage(EventsStorage eventsStorage, Time time) {
        this(eventsStorage, time, new HashMap<Class<?>, Long>());
    }

    public TimeExpiringEventsStorage(Time time) {
        this(new DefaultEventsStorage(), time, new HashMap<Class<?>, Long>());
    }

    public TimeExpiringEventsStorage(EventsStorage eventsStorage, Time time, Map<Class<?>, Long> objectClassToTtlMapping) {
        this.eventsStorage = eventsStorage;
        this.time = time;
        this.objectClassToTtlMapping = objectClassToTtlMapping;
    }

    public void removeExpiredEvents() {
        for (Event event : eventsStorage.getEvents()) {
            if (hasEventExpired(event)) {
                LOGGER.debug("Removing expired event:" + event);
                removeEvent(event);
            }
        }
    }

    private boolean hasEventExpired(Event event) {
        Time creationTime = event.getCreationTime();
        long ttl = getEventTtl(event);
        return creationTime.getTimeMillis() + ttl < time.getTimeMillis();
    }

    private long getEventTtl(Event event) {
        Long value = objectClassToTtlMapping.get(event.getObject());
        return (value == null) ? DEFAULT_TTL_MILLIS : value.longValue();
    }

    @Override
    public int getEventsCount() {
        return eventsStorage.getEventsCount();
    }

    @Override
    public List<Event> getEvents() {
        return eventsStorage.getEvents();
    }

    @Override
    public void addEvent(Event event) {
        removeExpiredEvents();
        eventsStorage.addEvent(event);
    }

    @Override
    public void removeEvent(Event event) {
        eventsStorage.removeEvent(event);
    }

}
