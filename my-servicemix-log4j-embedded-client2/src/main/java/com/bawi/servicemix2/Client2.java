package com.bawi.servicemix2;

import com.bawi.servicemix.logging.MyLogger;
import com.bawi.servicemix.logging.MyLoggerRepository;

public class Client2 {

    private MyLogger logger = MyLoggerRepository.getLogger(Client2.class);

    public Client2() throws InterruptedException {
        for (int i = 0; i < 300; i++) {
            logger.error("Hello World from client2: " + i);
            Thread.sleep(500);
        }
    }
}
