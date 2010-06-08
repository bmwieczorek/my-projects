package com.bawi.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class RequestForwardingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(RequestForwardingServlet.class);
    private final static String targetContext = "/service";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        forwardRequestToContext(req, resp, targetContext);
    }

    private void forwardRequestToContext(HttpServletRequest req, HttpServletResponse resp,
            String targetContext) throws ServletException, IOException {
        String originContext = req.getContextPath();
        String requestPathWithoutContext = req.getPathInfo();
        String myParameter = req.getParameter("myParameter");
        req.setAttribute("myAttribute", "attribute-set-in-request-forwarder-only-for-testing");

        if ("local".equals(myParameter)) {
            String targetJsp = "/next.jsp";
            logger.debug("Forwarding request inside context " + originContext + " from "
                    + requestPathWithoutContext + " to " + targetJsp);
            req.getRequestDispatcher(targetJsp).forward(req, resp);
        } else {
            ServletContext context = getServletContext().getContext(targetContext);
            validate(context);
            logger.debug("Forwarding request from " + originContext + requestPathWithoutContext + " to "
                    + targetContext + requestPathWithoutContext);
            context.getRequestDispatcher("/" + requestPathWithoutContext).forward(req, resp);
        }
    }

    private static void validate(ServletContext context) {
        if (context == null) {
            String message = "No deployable at target context " + targetContext;
            logger.error(message);
            throw new RuntimeException(message);
        }
    }
}
