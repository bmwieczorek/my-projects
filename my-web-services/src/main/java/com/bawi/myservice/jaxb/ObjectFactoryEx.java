package com.bawi.myservice.jaxb;


public class ObjectFactoryEx extends ObjectFactory {
	
    @Override
	public NewOperationRequest createNewOperationRequest() {
        return new NewOperationRequestEx();
    }

}
