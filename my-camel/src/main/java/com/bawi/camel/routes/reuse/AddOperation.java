package com.bawi.camel.routes.reuse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddOperation {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AddOperation.class);

	public void introduce(String name){
		LOGGER.info("Add operation says hello to {}", name);		
	}

}
