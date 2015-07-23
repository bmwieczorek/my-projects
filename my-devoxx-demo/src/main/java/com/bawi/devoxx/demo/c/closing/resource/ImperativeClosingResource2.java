package com.bawi.devoxx.demo.c.closing.resource;

public class ImperativeClosingResource2 {

    public static class Resource2 implements AutoCloseable {
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

        @Override
        public void close() {
            System.out.println("in close");
        }
    }

    public static void main(String[] args) {
        try (Resource2 resource = new Resource2()) {
            resource.op1();
            resource.op2();
        }

    }
}
