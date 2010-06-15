package com.bawi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class AboutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(AboutServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        PrintWriter writer = resp.getWriter();
        // Properties properties = PropertiesFileReader.readPropertiesFromResource("my.properties");
        // Set<String> stringPropertyNames = properties.stringPropertyNames();
        // for (String propertyName : stringPropertyNames) {
        // String message = propertyName + "=" + properties.getProperty(propertyName);
        // writer.println(message);
        // logger.debug(message);
        // }
        writer.flush();
        writer.close();
    }
}
