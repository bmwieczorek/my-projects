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
        String webAppRootKeyServletContext = "ServletContext init parameter: webAppRootKey="
                + getServletContext().getInitParameter("webAppRootKey");
        logger.debug(webAppRootKeyServletContext);
        writer.println(webAppRootKeyServletContext);

        String webAppRootKeyServletConfig = "ServletConfig init parameter: webAppRootKey="
                + getServletConfig().getInitParameter("webAppRootKey");
        logger.debug(webAppRootKeyServletConfig);
        writer.println(webAppRootKeyServletConfig);

        String webAppRootKeySystemProperty = "System property: webAppRootKey="
                + System.getProperty("webAppRootKey");
        logger.debug(webAppRootKeySystemProperty);
        writer.println(webAppRootKeySystemProperty);
        writer.flush();
        writer.close();
    }
}
