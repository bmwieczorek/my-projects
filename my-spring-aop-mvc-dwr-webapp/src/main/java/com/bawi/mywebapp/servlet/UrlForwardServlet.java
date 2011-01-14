/*
 * @(#)MyForwardServlet.java Nov 12, 2008
 * 
 * Copyright (c) 2006 TomTom International B.V. All rights reserved.
 * TomTom PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.bawi.mywebapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bartosz Wieczorek
 * @Nov 12, 2008
 * 
 */
public class UrlForwardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // RequestDispatcher requestDispatcher =
        // req.getRequestDispatcher("patch-distribution/patches");
        RequestDispatcher requestDispatcher = req.getSession().getServletContext().getContext("/patch-distribution")
                .getRequestDispatcher("/patches");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
