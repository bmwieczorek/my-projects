package com.bawi.drools.order;

import com.bawi.drools.order.OrderRQ;

/**
 * Created by SG0212148 on 19-Jan-16.
 */
public class CountryDiscountAction {

    public static void execute(OrderRQ orderRQ) {
        System.out.println("Executing action " + orderRQ);
    }
}
