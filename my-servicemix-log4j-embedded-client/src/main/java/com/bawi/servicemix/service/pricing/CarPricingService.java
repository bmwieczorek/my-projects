package com.bawi.servicemix.service.pricing;

public interface CarPricingService {

    boolean isPaymentSufficientForBrand(String brand, int payment);

}
