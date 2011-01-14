package com.bawi.servicemix.logging.context;

public final class LoggableContextProvider {

    private static ThreadLocal<LoggableContext> loggableContextThreadLocal = new ThreadLocal<LoggableContext>() {
        @Override
        protected LoggableContext initialValue() {
            return new LoggableContextImpl() {
            };
        }
    };

    private LoggableContextProvider() {
    }

    public static LoggableContext getContext() {
        return loggableContextThreadLocal.get();
    }

}
