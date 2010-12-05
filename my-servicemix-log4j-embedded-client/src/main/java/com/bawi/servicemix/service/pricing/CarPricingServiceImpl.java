package com.bawi.servicemix.service.pricing;

import java.util.HashMap;
import java.util.Map;

import com.bawi.servicemix.logging.MyLogger;

public class CarPricingServiceImpl implements CarPricingService {

    private final MyLogger logger = new MyLogger(CarPricingServiceImpl.class);
    private final Map<String, Integer> carPrices = new HashMap<String, Integer>();
    {
        carPrices.put("toyota", 10);
    }

    public boolean isPaymentSufficientForBrand(String car, int payment) {
        validate(payment);
        return isPaymentEnough(car, payment);
    }

    private void validate(int payment) {
        if (payment <= 0) {
            String errorMessage = "Payment  must be positive: " + payment;
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private boolean isPaymentEnough(String car, int payment) {
        boolean isPaymentEnough = getCarPrice(car) >= payment;
        if (isPaymentEnough) {
            logger.debug(car + " sold for " + payment);
        } else {
            logger.debug(car + " cannot be sold for " + payment);
        }
        return isPaymentEnough;
    }

    private int getCarPrice(String car) {
        logger.debug("Searching price for " + car);
        int price = carPrices.get(car);
        logger.debug(car + " price is " + price);
        return price;
    }
}
