package com.bawi.eventsfilter.impl;

import com.bawi.eventsfilter.Event;
import com.bawi.eventsfilter.EventsFactory;
import com.bawi.eventsfilter.Time;

public class DefaultEventsFactory implements EventsFactory {

    private final Time time;
    private long eventsCount;

    public DefaultEventsFactory() {
        this(new Time());
    }

    public DefaultEventsFactory(Time time) {
        this.time = time;
    }

    @Override
    public Event createEvent(Object object) {
        return new Event(eventsCount++, object, time);
    }

}
