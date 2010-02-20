package com.bawi.services.service.validator;

import com.bawi.services.service.exception.InvalidRequestException;

public interface RequestValidator {
	
	public void validate() throws InvalidRequestException;

}
