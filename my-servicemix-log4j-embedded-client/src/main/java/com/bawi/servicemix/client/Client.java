package com.bawi.servicemix.client;

import com.bawi.servicemix.logging.MyLogger;
import com.bawi.servicemix.logging.MyLoggerRepository;
import com.bawi.servicemix.service.CarShopService;

public class Client {

    private MyLogger logger = MyLoggerRepository.getLogger(Client.class);

    public Client(final CarShopService carShopService) {
        System.out.println("Client started");
        logger.debug("Client started");
        buyCarWhenEnoughMoney(carShopService);
        buyCarWhenNotEnoughMoney(carShopService);
        logger.debug("Client stopped");
        System.err.println("Client stopped");
    }

    private void buyCarWhenEnoughMoney(final CarShopService carShopService) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                carShopService.orderCar("toyota", -1);
            }
        }).start();
    }

    private void buyCarWhenNotEnoughMoney(final CarShopService carShopService) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                carShopService.orderCar("bmw", -10);
            }
        }).start();
    }

}
