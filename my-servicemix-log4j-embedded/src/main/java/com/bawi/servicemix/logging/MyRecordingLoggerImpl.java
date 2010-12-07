package com.bawi.servicemix.logging;

import com.bawi.servicemix.logging.context.LoggableContext;
import com.bawi.servicemix.logging.context.LoggableContextProvider;

public class MyRecordingLoggerImpl extends MyLoggerImpl {

    MyRecordingLoggerImpl(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public void debug(String message) {
        saveMessage(message);
        super.debug(message);
    }

    @Override
    public void info(String message) {
        saveMessage(message);
        super.info(message);
    }

    @Override
    public void error(String message) {
        saveMessage(message);
        super.error("\n" + getAllMessagesContent());
    }

    private void saveMessage(String message) {
        LoggableContext context = LoggableContextProvider.getContext();
        context.addMessage(message);
    }

    private String getAllMessagesContent() {
        LoggableContext context = LoggableContextProvider.getContext();
        return context.getAllMessagesAsString();
    }

}
