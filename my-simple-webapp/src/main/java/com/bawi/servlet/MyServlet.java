package com.bawi.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

public class MyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(MyServlet.class);

    @Override
    public void init() throws ServletException {
        logger.debug("myProperty=" + System.getProperty("myProperty"));
    }

}
