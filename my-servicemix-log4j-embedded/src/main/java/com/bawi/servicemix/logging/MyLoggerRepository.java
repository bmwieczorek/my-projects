package com.bawi.servicemix.logging;

public class MyLoggerRepository {

    public static MyLogger getLogger(Class<?> clazz) {
        // return new MyLoggerImpl(clazz);
        return new MyRecordingLoggerImpl(clazz);
    }

}
