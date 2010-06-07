package com.bawi.mywebapp.mvc;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bawi.mywebapp.domain.Product;
import com.bawi.mywebapp.dwr.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloController implements Controller {

    protected final Log logger = LogFactory.getLog(getClass());

    Server server;

    public void setServer(Server server) {
        this.server = server;
    }

    int value;

    public void setValue(int value) {
        this.value = value;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, Object> model = new HashMap<String, Object>();
        String now = (new Date()).toString();
        logger.info("Returning hello view with " + now);
        server.welcomeUser("ania");
        model.put("now", now);

        // default search in webapp for jsp pages
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Car", 100));
        products.add(new Product("House", 1000));
        products.add(new Product("Name", value));
        model.put("products", products);

        return new ModelAndView("hello", "model", model);

        // or
        // return new ModelAndView("WEB-INF/jsp/hello.jsp");
    }

}