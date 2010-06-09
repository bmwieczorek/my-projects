package com.bawi.services.calculator;

import static junit.framework.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

import com.bawi.services.calculator.runner.ServiceRunner;

public class ServiceRunnerIntegrationTest {

    @Test
    public void shouldStartServiceRunner() throws Exception {
        Thread serviceRunner = createServiceRunnerThread();
        serviceRunner.start();

        Thread jetty = createMavenJettyThread();
        jetty.start();

        // Thread.sleep(7000);
        validateServiceIsUp("http://localhost:7890/calculator?wsdl");
        validateServiceIsUp("http://localhost:7890/calculator?wsdl");

        jetty.join();
        serviceRunner.join();
    }

    private void validateServiceIsUp(String url) throws IOException, HttpException {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        int statusCode = client.executeMethod(method);
        assertEquals(HttpStatus.SC_OK, statusCode);
        String responseBody = method.getResponseBodyAsString();
        System.out.println(responseBody);
    }

    private Thread createServiceRunnerThread() {
        Thread serviceRunner = new Thread(new Runnable() {
            @Override
            public void run() {
                ServiceRunner.main(null);
            }

        });
        return serviceRunner;
    }

    private Thread createMavenJettyThread() {
        Thread serviceRunner = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    startMavenJettyRun();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        return serviceRunner;
    }

    private void startMavenJettyRun() throws IOException {
        String s = null;
        Process mavenProcess = Runtime.getRuntime().exec("mvn cargo:start");
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(mavenProcess.getInputStream()));
        BufferedReader stdOut = new BufferedReader(new InputStreamReader(mavenProcess.getErrorStream()));
        while ((s = stdIn.readLine()) != null) {
            System.out.println(s);
        }

        while ((s = stdOut.readLine()) != null) {
            System.err.println(s);
        }
    }

}
