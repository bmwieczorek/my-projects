package com.bawi.servicemix.logging.context;

import java.util.List;

public interface LoggableContext {

    void addLoggableEvent(String event);

    List<String> getAllEvents();

    String getAllEventsAsLogString();

    void clear();

}
