package com.bawi.servlet;

import static com.bawi.servlet.RequestForwardingServlet.Context.CROSS;
import static com.bawi.servlet.RequestForwardingServlet.Context.LOCAL;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class RequestForwardingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    public enum Context {
        LOCAL, CROSS
    };

    private final static Logger logger = Logger.getLogger(RequestForwardingServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String myParameter = req.getParameter("myParameter");

        req.setAttribute("myAttribute", "Bartek");
        // validateParameter(myParameter);

        if (CROSS.toString().equals(myParameter)) {
            String targetContext = "/service";
            forwardRequestToOtherContext(req, resp, targetContext);
        }
        if (LOCAL.toString().equals(myParameter)) {
            String pagePath = "/next.jsp";
            forwardRequestInsideContext(req, resp, pagePath);
        }


    }

    private void forwardRequestToOtherContext(HttpServletRequest req, HttpServletResponse resp, String targetContext)
            throws ServletException, IOException {

        ServletContext context = getServletContext().getContext(targetContext);
        validate(context, targetContext);


        String originContext = req.getContextPath();
        String requestPathWithoutContext = req.getPathInfo();
        logger.debug("Forwarding request from " + originContext + requestPathWithoutContext + " to " + targetContext
                + requestPathWithoutContext);
        context.getRequestDispatcher("/" + requestPathWithoutContext).forward(req, resp);

    }

    private void forwardRequestInsideContext(HttpServletRequest req, HttpServletResponse resp, String pagePath)
            throws ServletException, IOException {

        String context = req.getContextPath();
        String requestPathWithoutContext = req.getPathInfo();
        logger.debug("Forwarding request inside context " + context + " from " + requestPathWithoutContext + " to "
                + pagePath);
        req.getRequestDispatcher(pagePath).forward(req, resp);

    }

    private static void validate(ServletContext context, String targetContext) {
        if (context == null) {
            String message = "No deployable at target context " + targetContext;
            logger.error(message);
            throw new RuntimeException(message);
        }
    }
}
