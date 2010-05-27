package com.bawi.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestForwardingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardRequestToContext(req, resp, "/service");
	}
	
    private void forwardRequestToContext(HttpServletRequest req, HttpServletResponse resp, String targetContext)
            throws ServletException, IOException {
        ServletContext context = getServletContext().getContext(targetContext);
        String requestPathWithoutContext = req.getPathInfo();
        context.getRequestDispatcher("/" + requestPathWithoutContext).forward(req, resp);
    }
}
