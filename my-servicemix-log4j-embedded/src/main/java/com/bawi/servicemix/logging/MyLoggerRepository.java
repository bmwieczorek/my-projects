package com.bawi.servicemix.logging;

public final class MyLoggerRepository {

    private MyLoggerRepository() {
    }

    public static MyLogger getLogger(Class<?> clazz) {
        // return new MyLoggerImpl(clazz);
        return new MyRecordingLoggerImpl(clazz);
    }

}
