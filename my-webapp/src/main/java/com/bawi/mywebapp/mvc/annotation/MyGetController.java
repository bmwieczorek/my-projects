package com.bawi.mywebapp.mvc.annotation;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Bartosz Wieczorek
 * @Nov 26, 2008
 * 
 */

@Controller
@RequestMapping("/status.an")
public class MyGetController {

    private static final String CONTENT_TYPE = "text/plain; charset=UTF-8";

    @RequestMapping(method = RequestMethod.GET)
    public void handleRequest(@RequestParam("version") int version, HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        String output = "Version=" + version;
        sendPost();

        response.setContentType(CONTENT_TYPE);

        response.getOutputStream().write(output.getBytes());
    }

    private void sendPost() throws HttpException, IOException {
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setConnectionManagerTimeout(5000);
        PostMethod postMethod = new PostMethod("http://dev-newyork-1:8080/map-corrections/submission");
        postMethod.setRequestHeader("rawSubmissionId", "1234");
        String h = "HelloWorld";
        RequestEntity requestEntity = new ByteArrayRequestEntity(h.getBytes("UTF-8"));
        postMethod.setRequestEntity(requestEntity);
        httpClient.executeMethod(postMethod);
    }

    byte[] readFile(String fileName) {
        return null;
    }
}
