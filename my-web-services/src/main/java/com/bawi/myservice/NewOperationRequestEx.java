package com.bawi.myservice;

import com.bawi.myservice.NewOperationRequest;

public class NewOperationRequestEx extends NewOperationRequest {
	
	public String print() {
		return myAtt + ":"  + intIn;
	}

}
