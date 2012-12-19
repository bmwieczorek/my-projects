package com.bawi.camel.routes.reuse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnsupportedOperation {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UnsupportedOperation.class);

	public void introduce(String name){
		LOGGER.info("Unsupported operation says hello to {}", name);		
	}

}
