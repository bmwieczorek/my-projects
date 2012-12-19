package com.bawi.camel.routes.reuse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBean {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyBean.class);

	public void introduce(String name){
		LOGGER.info("My bean says hello to {}", name);		
	}

	
}
