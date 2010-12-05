package com.bawi.servicemix.service;

import com.bawi.servicemix.logging.MyLogger;
import com.bawi.servicemix.service.availability.CarAvailabilityService;
import com.bawi.servicemix.service.pricing.CarPricingService;

public class CarShopServiceImpl implements CarShopService {

    private final MyLogger logger = new MyLogger(CarShopServiceImpl.class);

    private final CarAvailabilityService availabilityService;
    private final CarPricingService pricingService;

    public CarShopServiceImpl(CarAvailabilityService availabilityService, CarPricingService pricingService) {
        this.availabilityService = availabilityService;
        this.pricingService = pricingService;
    }

    public boolean orderCar(String brand, int payment) {
        logger.debug("Ordering car " + brand + " for amount" + payment);
        try {
            return availabilityService.isCarAvailable(brand)
                    && pricingService.isPaymentSufficientForBrand(brand, payment);
        } catch (Exception e) {
            logger.error("Problem with ordering a car " + e.getCause());
            return false;
        }
    }
}
