package com.bawi.mywebservices.service.validator;

import com.bawi.mywebservices.service.exception.InvalidRequestException;

public interface RequestValidator {
	
	public void validate() throws InvalidRequestException;

}
