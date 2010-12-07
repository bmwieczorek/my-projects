package com.bawi.servicemix.logging.context;

import java.util.List;

public interface LoggableContext {

    void addMessage(String message);

    List<String> getAllMessages();

    String getAllMessagesAsString();

    void clear();

}
