package com.bawi.servicemix.client;

import com.bawi.servicemix.logging.MyLogger;
import com.bawi.servicemix.service.CarShopService;

public class Client {

    private MyLogger logger = new MyLogger(Client.class);

    public Client(CarShopService carShopService) throws InterruptedException {
        logger.debug("Client started");
        carShopService.orderCar("toyota", 100);
        carShopService.orderCar("toyota", -1);
    }
}
