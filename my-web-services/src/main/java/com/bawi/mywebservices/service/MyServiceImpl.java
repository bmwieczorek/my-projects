package com.bawi.mywebservices.service;

import com.bawi.myservice.MyServiceInterface;
import com.bawi.myservice.NewOperationRequest;
import com.bawi.myservice.NewOperationResponse;

public class MyServiceImpl implements MyServiceInterface {

    @Override
    public NewOperationResponse newOperation(NewOperationRequest parameters) {
        System.out.println("In impl !!!!");
        return null;
    }

}
