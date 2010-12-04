package com.bawi.servicemix;

import com.bawi.servicemix.logging.MyLogger;

public class Client {

    private MyLogger logger = new MyLogger(Client.class);

    public Client() {
        logger.error("Hello World from client");
    }
}
