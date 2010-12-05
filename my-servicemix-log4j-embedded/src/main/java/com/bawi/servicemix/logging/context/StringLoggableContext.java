package com.bawi.servicemix.logging.context;

import java.util.ArrayList;
import java.util.List;

public abstract class StringLoggableContext implements LoggableContext {

    private static String HOST = "localhost";
    private String id = " [" + System.currentTimeMillis() + "-" + HOST + "] ";

    private List<String> events = new ArrayList<String>();

    public void addLoggableEvent(String event) {
        events.add(event);
    }

    public List<String> getAllEvents() {
        return events;
    }

    public String getAllEventsAsLogString() {
        StringBuilder logs = new StringBuilder();
        for (String event : events) {
            logs.append(id);
            logs.append(event.toString());
        }
        return logs.toString();
    }

    public void clear() {
        events = new ArrayList<String>();
    }
}
