package com.bawi.mywebapp.dwr;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class ServerImpl implements Server{

	protected final Log logger = LogFactory.getLog(getClass());
	
	public String welcomeUser(String username) {
		Date date = new Date();
		logger.info("ServerImpl: I am in the method welcomeUser");
		return "Welcome " + username + " " + date.toString();
	}
	
	
}
