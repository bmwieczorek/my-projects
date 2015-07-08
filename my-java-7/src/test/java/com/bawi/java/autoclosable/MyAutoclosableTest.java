package com.bawi.java.autoclosable;

import java.util.Date;

import org.junit.Test;

class MyAutoclosable implements AutoCloseable {

    public void doPass() {
        System.out.println(new Date() + " doPass called");
    }

    public void doFail() {
        throw new RuntimeException(new Date() + " doFail called");
    }

    @Override
    public void close() throws Exception {
        System.out.println(new Date() + " close called");
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
    public void shouldFailWithTryWithResources() throws Exception {
        try (MyAutoclosable myAutoclosable = new MyAutoclosable()) {
            myAutoclosable.doFail();
        }
    }
}
