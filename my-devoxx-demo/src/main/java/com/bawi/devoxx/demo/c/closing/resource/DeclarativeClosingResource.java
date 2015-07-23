package com.bawi.devoxx.demo.c.closing.resource;

import java.util.function.Consumer;

public class DeclarativeClosingResource {
    public static class Resource3 {
        private Resource3() {}
        public static Resource3 use(Consumer<Resource3> consumer) {
            Resource3 resource3 = new Resource3();
            try {
                consumer.accept(resource3);
            } finally {
                resource3.close();
            }
            return resource3;
        }
        public void op1() {
            System.out.println("in op1");
        };

        public void op2() {
            System.out.println("in op2");
        };
        private void close() {
            System.out.println("in close");
        }
    }

    public static void main(String[] args) {
        Resource3.use(resource -> {
           resource.op1(); 
           resource.op2();
        });
    }
}
