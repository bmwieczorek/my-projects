package com.bawi.myservice.jaxb;

import com.bawi.myservice.jaxb.NewOperationRequest;

public class NewOperationRequestEx extends NewOperationRequest {
	
	public String print() {
		return myAtt + ":"  + intIn;
	}

}
