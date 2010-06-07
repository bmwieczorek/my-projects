package com.bawi.mywebapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

class MyClass {
    String name = "ania";

    @Override
    public String toString() {
        return getClass().getName() + "name=" + name;
    }

    public MyClass(String name) {
        this.name = name;
    }
}

public class MyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(MyServlet.class);

    public void init() throws ServletException {
        Properties properties = System.getProperties();
        Set<Object> keySet = properties.keySet();
        StringBuilder builder = new StringBuilder("\n");
        for (Object key : keySet) {
            // builder.append(key + "=" + properties.get(key)+ "\n");
            if (((String) key).startsWith("jboss")) {
                builder.append(key + "=" + properties.get(key) + "\n");
            }
        }
        log.info(builder);
        log.info(this.getServletContext().getInitParameter("contextConfigLocation"));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("hello world!\n");
        HttpSession session = req.getSession();
        out.println("Id=" + session.getId());
        Enumeration attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = (String) attributeNames.nextElement();
            String value = session.getAttribute(name).toString();
            out.println("name=" + name + ",value=" + value);
        }
        out.println("ServletContext=" + session.getServletContext());
        out.println("CreationTime=" + new Date(session.getCreationTime()));
        out.println("MaxInactiveInterval=" + session.getMaxInactiveInterval());
        Enumeration parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = (String) parameterNames.nextElement();
            String value = req.getParameter(name);
            out.println("name=" + name + ",value=" + value);
        }
        session.setAttribute("bawi", new MyClass("basia"));
        // session.putValue("bawi2", new MyClass("basia2"));

    }

    // private void sendPost() throws HttpException, IOException{
    // HttpClient httpClient = new HttpClient();
    // PostMethod postMethod = new
    // PostMethod("http://localhost:8080/my-servlet/my-servlet-url2");
    // postMethod.setRequestEntity(requestEntity);
    // int executeMethod = httpClient.executeMethod(postMethod);
    // }

}
