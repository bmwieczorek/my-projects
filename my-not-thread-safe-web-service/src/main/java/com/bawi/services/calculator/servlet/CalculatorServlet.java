package com.bawi.services.calculator.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalculatorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        runServicesFromApplicationContextOnServletStartup();
    }

    private void runServicesFromApplicationContextOnServletStartup() {
        String[] configLocations = new String[] { "cxf-context.xml", "services-context.xml" };
        new ClassPathXmlApplicationContext(configLocations);
    }
}
