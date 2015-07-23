package com.bawi.devoxx.demo.c.closing.resource;

public class ImperativeClosingResource {

    public static class Resource {
        public void op1() {
            System.out.println("in op1");
        };

        public void op2() {
            System.out.println("in op2");
        };
        
        @Override
        protected void finalize() throws Throwable {
            System.out.println("in finalinze");
        };
        public void close() {
            System.out.println("in close");
        }
    }

    public static void main(String[] args) {
        Resource resource = new Resource();
        try {
            resource.op1();
            resource.op2();
        } finally {
            resource.close();
        }
    }
}
