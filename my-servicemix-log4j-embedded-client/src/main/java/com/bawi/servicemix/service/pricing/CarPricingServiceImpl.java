package com.bawi.servicemix.service.pricing;

import java.util.HashMap;
import java.util.Map;

import com.bawi.servicemix.logging.MyLogger;
import com.bawi.servicemix.logging.MyLoggerRepository;

public class CarPricingServiceImpl implements CarPricingService {

    private final MyLogger logger = MyLoggerRepository.getLogger(CarPricingServiceImpl.class);
    private final Map<String, Integer> carPrices = new HashMap<String, Integer>();
    {
        carPrices.put("toyota", 10);
        carPrices.put("bmw", 20);
    }

    public boolean isPaymentSufficientForBrand(String car, int payment) {
        validate(payment);
        return isPaymentEnough(car, payment);
    }

    private void validate(int payment) {
        if (payment <= 0) {
            String errorMessage = "Payment must be positive but was " + payment;
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private boolean isPaymentEnough(String car, int payment) {
        boolean isPaymentEnough = payment >= getCarPrice(car);
        logger.debug("Payment " + payment + " for " + car + (isPaymentEnough ? " is" : " is not") + " enough");
        return isPaymentEnough;
    }

    private int getCarPrice(String car) {
        logger.debug("Searching price for " + car);
        int price = carPrices.get(car);
        logger.debug("Car " + car + " price is " + price);
        return price;
    }
}
