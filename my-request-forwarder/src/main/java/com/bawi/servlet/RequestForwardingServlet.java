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

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logRequestDetails(req);
        setRequestAttributeForTestingPurposesNotRequiredForForwarding(req);

        String myParameter = req.getParameter("myParameter");
        if ("local".equals(myParameter)) {
            forwardToOtherPathInTheSameContext(req, resp);
        } else {
            forwardToTheSamePathInOtherContext(req, resp);
        }
    }

    private void logRequestDetails(HttpServletRequest req) {
        // ContextPath=/forwarder, PathInfo=/hello.jsp, RequestURI=/forwarder/do/hello.jsp, ServletPath=/do,
        // QueryString=myParameter=local
        logger.debug("ContextPath=" + req.getContextPath());
        logger.debug("PathInfo=" + req.getPathInfo());
        logger.debug("RequestURI=" + req.getRequestURI());
        logger.debug("ServletPath=" + req.getServletPath());
        logger.debug("QueryString=" + req.getQueryString());
    }

    private void setRequestAttributeForTestingPurposesNotRequiredForForwarding(HttpServletRequest req) {
        req.setAttribute("myAttribute", "attribute-set-in-request-forwarder-only-for-testing");
    }

    private void forwardToOtherPathInTheSameContext(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String originContextName = req.getContextPath();
        String requestedResource = req.getPathInfo();
        String forwardedResource = "/next.jsp";
        logger.debug("Forwarding request inside context " + originContextName + " from " + requestedResource + " to "
                + forwardedResource);
        req.getRequestDispatcher(forwardedResource).forward(req, resp);
    }

    private void forwardToTheSamePathInOtherContext(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String originContextName = req.getContextPath();
        String requestedResource = req.getPathInfo();
        String targetContextName = "/service";
        ServletContext context = getServletContext().getContext(targetContextName);
        if (context == null) {
            String message = "No deployable at target context " + targetContextName;
            logger.error(message);
            throw new RuntimeException(message);
        }
        logger.debug("Forwarding request from " + originContextName + requestedResource + " to " + targetContextName
                + requestedResource);
        context.getRequestDispatcher("/" + requestedResource).forward(req, resp);
    }
}
