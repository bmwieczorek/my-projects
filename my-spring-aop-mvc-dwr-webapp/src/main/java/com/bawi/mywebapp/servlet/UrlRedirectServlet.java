package com.bawi.mywebapp.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bartosz Wieczorek
 * @Nov 12, 2008
 */
public class UrlRedirectServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * The init method of the servlet.
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /**
     * This method processes the HttpServletRequest. Here the method of using
     * sendRedirect() method is shown for the purpose of URL redirection.
     */
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/patch-distribution/patches");

    }
}
