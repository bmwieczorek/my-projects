package com.bawi.mywebservices;

import com.bawi.myservice.NewOperationRequest;
import com.bawi.myservice.ObjectFactory;

public class ObjectFactoryEx extends ObjectFactory {

    @Override
    public NewOperationRequest createNewOperationRequest() {
        return new NewOperationRequestEx();
    }

}
