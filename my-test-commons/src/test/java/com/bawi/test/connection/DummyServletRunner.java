package com.bawi.test.connection;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.testing.ServletTester;

public class DummyServletRunner {

	public static class DummyServlet extends HttpServlet {

		private static final long serialVersionUID = 1L;

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			printOk(resp);
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			printOk(resp);
		}

		private void printOk(HttpServletResponse resp) throws IOException {
			PrintWriter writer = resp.getWriter();
			writer.println("Ok");
			writer.flush();
		}

	}

	private final static String defaultContextPath = "/test";

	private final String url;
	private final ServletTester servletTester;
	private final String contextPath;

	public DummyServletRunner() throws Exception {
		this(defaultContextPath);
	}

	public DummyServletRunner(String contextPath) throws Exception {
		this.contextPath = contextPath;
		servletTester = new ServletTester();
		servletTester.setContextPath("/");
		servletTester.addServlet(DummyServlet.class, contextPath);
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
