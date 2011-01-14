package com.bawi.mywebapp.dwr;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServerImpl implements Server {

    private static final Log LOGGER = LogFactory.getLog(ServerImpl.class);

    @Override
    public String welcomeUser(String username) {
        Date date = new Date();
        LOGGER.info("ServerImpl: I am in the method welcomeUser");
        return "Welcome " + username + " " + date.toString();
    }

}
