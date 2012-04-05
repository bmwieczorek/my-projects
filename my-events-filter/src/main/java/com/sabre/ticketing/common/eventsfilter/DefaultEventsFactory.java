package com.sabre.ticketing.common.eventsfilter;


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
