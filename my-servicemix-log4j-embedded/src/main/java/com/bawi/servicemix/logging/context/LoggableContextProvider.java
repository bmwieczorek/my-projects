package com.bawi.servicemix.logging.context;

public class LoggableContextProvider {

    static private ThreadLocal<LoggableContext> loggableContextThreadLocal = new ThreadLocal<LoggableContext>() {
        @Override
        protected LoggableContext initialValue() {
            return new LoggableContextImpl() {
            };
        }
    };

    public static LoggableContext getContext() {
        return loggableContextThreadLocal.get();
    }

}
