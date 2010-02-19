package com.bawi.mywebservices.processor;

import com.bawi.myservice.NewOperationRequest;

public class NewOperationRequestEx extends NewOperationRequest {
	
	public String print() {
		return myAtt + ":"  + intIn;
	}

}
