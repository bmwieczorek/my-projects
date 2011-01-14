package com.bawi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bawi.WarMainPropertiesFileReader;

public class AboutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(AboutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Properties properties = WarMainPropertiesFileReader.readPropertiesFromResource("my-war-main.properties");
        Set<String> stringPropertyNames = properties.stringPropertyNames();
        for (String propertyName : stringPropertyNames) {
            String message = propertyName + "=" + properties.getProperty(propertyName);
            writer.println(message);
            logger.debug(message);
        }
        writer.flush();
        writer.close();
    }
}
