package com.bawi.servicemix.logging.context;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

import java.util.ArrayList;
import java.util.List;

public abstract class LoggableContextImpl implements LoggableContext {

    private static final String HOST = "localhost";
    private String id = " [" + currentThread().getName() + "-" + currentTimeMillis() + "-" + HOST + "] ";

    private List<String> events = new ArrayList<String>();

    @Override
    public void addMessage(String event) {
        events.add(event);
    }

    @Override
    public List<String> getAllMessages() {
        return events;
    }

    @Override
    public String getAllMessagesAsString() {
        StringBuilder logs = new StringBuilder();
        for (String event : events) {
            logs.append(id);
            logs.append(event.toString());
            logs.append("\n");
        }
        return logs.toString();
    }

    @Override
    public void clear() {
        events = new ArrayList<String>();
    }
}
