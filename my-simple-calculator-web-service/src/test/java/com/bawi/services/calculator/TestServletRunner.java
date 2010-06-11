package com.bawi.services.calculator;

import org.mortbay.jetty.testing.ServletTester;

public class TestServletRunner {

    private final static String defaultContextPath = "/test";

    private final String url;
    private final ServletTester servletTester;
    private final String contextPath;

    public TestServletRunner() throws Exception {
        this(defaultContextPath);
    }

    public TestServletRunner(String contextPath) throws Exception {
        this.contextPath = contextPath;
        servletTester = new ServletTester();
        servletTester.setContextPath("/");
        servletTester.addServlet(TestServlet.class, contextPath);
        url = servletTester.createSocketConnector(true);
    }

    public void start() throws Exception {
        servletTester.start();
    }

    public void stop() throws Exception {
        servletTester.stop();
    }

    public String getUrl() {
        return url + contextPath;
    }
}
