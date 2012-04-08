package com.sabre.ticketing.common.eventsfilter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;



public class DefaultEventsStorage implements EventsStorage {

    private final List<Event> events;

    public DefaultEventsStorage() {
        this(new CopyOnWriteArrayList<Event>());
    }

    public DefaultEventsStorage(List<Event> events) {
        this.events = events;
    }

    @Override
    public int getEventsCount() {
        return events.size();
    }

    @Override
    public List<Event> getEvents() {
        return events;
    }

    @Override
    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public void removeEvent(Event event) {
        events.remove(event);
    }

}
