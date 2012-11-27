package com.bawi.camel.event;

import java.util.EventObject;

import org.apache.camel.management.EventNotifierSupport;

public class MyCamelEventNotifier extends EventNotifierSupport {

    @Override
    public void notify(EventObject event) throws Exception {
        System.out.println(event);
    }

    @Override
    public boolean isEnabled(EventObject event) {
        return true;
    }

    @Override
    protected void doStart() throws Exception {
    }

    @Override
    protected void doStop() throws Exception {
    }

}
