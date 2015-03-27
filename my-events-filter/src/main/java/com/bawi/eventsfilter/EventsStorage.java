package com.bawi.eventsfilter;

import java.util.List;

public interface EventsStorage {

    int getEventsCount();

    List<Event> getEvents();

    void addEvent(Event event);

    void removeEvent(Event event);

}
