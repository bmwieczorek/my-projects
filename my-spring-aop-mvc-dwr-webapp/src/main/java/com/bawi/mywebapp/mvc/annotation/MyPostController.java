package com.bawi.mywebapp.mvc.annotation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Bartosz Wieczorek
 * @Nov 26, 2008
 * 
 */

@Controller
@RequestMapping("/submission.an")
public class MyPostController {

    @RequestMapping(method = RequestMethod.POST)
    public void handleRequest(HttpServletRequest request, @SuppressWarnings("unused") HttpServletResponse response)
            throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        ServletInputStream inputStream = request.getInputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) >= 0) {
            result.write(buf, 0, len);
        }

        System.err.println("***");
        System.err.println(request.getAttribute("submissionId"));
        System.err.println(request.getHeader("submissionId"));
        System.err.println(request.getParameter("submissionId"));
        System.err.println(result);
        System.err.println("###");

    }
}
