package com.bawi.myservice;



public class ObjectFactoryEx extends ObjectFactory {
	
    @Override
	public NewOperationRequest createNewOperationRequest() {
        return new NewOperationRequestEx();
    }

}
