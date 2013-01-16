package com.bawi.camel.timeout.processor;

public class BusinessObjectCreatorStub {
	
	private BusinessObject businessObject;

	public BusinessObject create(String text) {
		return businessObject;
	}

	public void setBusinessObject(BusinessObject businessObject) {
		this.businessObject = businessObject;
	}

}
