package com.bawi.myspringapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bawi.myspringapp.MyService;

public class MyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void init() throws ServletException {
        createService();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        createService();
    }

    private void createService() {
        new MyService();
    }
}
