package com.bawi.servicemix.service;

import com.bawi.servicemix.logging.MyLogger;
import com.bawi.servicemix.logging.MyLoggerRepository;
import com.bawi.servicemix.service.availability.CarAvailabilityService;
import com.bawi.servicemix.service.pricing.CarPricingService;

public class CarShopServiceImpl implements CarShopService {

    private final MyLogger logger = MyLoggerRepository.getLogger(CarShopServiceImpl.class);

    private final CarAvailabilityService availabilityService;
    private final CarPricingService pricingService;

    public CarShopServiceImpl(CarAvailabilityService availabilityService, CarPricingService pricingService) {
        this.availabilityService = availabilityService;
        this.pricingService = pricingService;
    }

    public boolean orderCar(String car, int payment) {
        logger.info("Ordering car " + car + " for amount " + payment);
        try {
            boolean isCarSold = availabilityService.isCarAvailable(car)
                    && pricingService.isPaymentSufficientForBrand(car, payment);
            logger.info("Car " + car + (isCarSold ? " sold" : " not sold") + " for " + payment);
            return isCarSold;
        } catch (Exception e) {
            logger.error("Problem with ordering a car " + e.getMessage());
            return false;
        }
    }
}
