package com.bawi.servicemix;

import org.apache.log4j.Logger;

public class Client {

    private static Logger logger = Logger.getLogger(Client.class);

    public Client() {
        logger.error("Error hello world Log4j embedded");
    }
}
