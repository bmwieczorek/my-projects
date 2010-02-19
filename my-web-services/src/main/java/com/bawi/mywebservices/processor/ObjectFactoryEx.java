package com.bawi.mywebservices.processor;

import com.bawi.myservice.NewOperationRequest;
import com.bawi.myservice.ObjectFactory;



public class ObjectFactoryEx extends ObjectFactory {
	
    @Override
	public NewOperationRequest createNewOperationRequest() {
        return new NewOperationRequestEx();
    }

}
