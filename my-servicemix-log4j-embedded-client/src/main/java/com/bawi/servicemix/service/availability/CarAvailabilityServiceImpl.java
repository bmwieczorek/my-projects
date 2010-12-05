package com.bawi.servicemix.service.availability;

import java.util.Arrays;
import java.util.List;

import com.bawi.servicemix.logging.MyLogger;

public class CarAvailabilityServiceImpl implements CarAvailabilityService {

    private final List<String> cars = Arrays.asList("toyota");
    private final MyLogger logger = new MyLogger(CarAvailabilityServiceImpl.class);

    public boolean isCarAvailable(String car) {
        logger.debug("Searching availability for " + car);

        boolean isAvailable = cars.contains(car.toLowerCase());
        if (isAvailable) {
            logger.debug(car + " is available");
        } else {
            logger.debug(car + " is NOT available");
        }

        return isAvailable;
    }
}
