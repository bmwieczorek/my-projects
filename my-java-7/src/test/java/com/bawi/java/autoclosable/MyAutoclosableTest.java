package com.bawi.java.autoclosable;

import java.io.IOException;

import org.junit.Test;

class MyAutoclosable implements AutoCloseable {

    public void doPass() {
        System.out.println("doPass called");
    }

    public void doFail() {
        throw new RuntimeException("doFail called");
    }

    @Override
    public void close() throws IOException { // originally throws Exception
        System.out.println("close called");
    }
}

public class MyAutoclosableTest {

    @Test
    public void shouldPass() throws Exception {
        @SuppressWarnings("resource")
        MyAutoclosable myAutoclosable = new MyAutoclosable();
        myAutoclosable.doPass();
    }

    @Test
    public void shouldPassWithFinally() throws Exception {
        MyAutoclosable myAutoclosable = new MyAutoclosable();
        try {
            myAutoclosable.doPass();
        } finally {
            myAutoclosable.close();
        }
    }

    @Test
    public void shouldPassWithTryWithResources() throws Exception {
        try (MyAutoclosable myAutoclosable = new MyAutoclosable()) {
            myAutoclosable.doPass();
        }
    }

    @Test(expected = RuntimeException.class)
    public void shouldFail() throws Exception {
        @SuppressWarnings("resource")
        MyAutoclosable myAutoclosable = new MyAutoclosable();
        myAutoclosable.doFail();
    }

    @Test(expected = RuntimeException.class)
    public void shouldFailWithFinally() {
        MyAutoclosable myAutoclosable = new MyAutoclosable();
        try {
            myAutoclosable.doFail();
        } finally {
            try {
                myAutoclosable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test(expected = RuntimeException.class)
    public void shouldFailWithTryWithResources() {
        try (MyAutoclosable myAutoclosable = new MyAutoclosable()) {
            myAutoclosable.doFail();
        } catch (IOException e) { // required catch/or re-throw IOException coming from close() throws IOException,
                                  // possible also catch(Exception e) which would catch RuntimeException from doFail() and also IOException from close()
            e.printStackTrace();
        }
        
        String a = "";
        if ("A".equals(a)) {
            
        }
    }
    // close called

}
