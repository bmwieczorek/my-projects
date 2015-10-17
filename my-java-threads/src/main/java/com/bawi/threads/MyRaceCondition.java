package com.bawi.threads;

public class MyRaceCondition {

    static class Counter {
        private long value = 0;

        public void add(long v) {
            value = value + v;
        }

        public long getValue() {
            return value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Counter counter = new Counter();

            Thread t1 = new Thread(() -> counter.add(1));
            Thread t2 = new Thread(() -> counter.add(2));
            t1.start();
            t2.start();

            t1.join();
            t2.join();

            long value = counter.getValue();
            System.out.println("value=" + value);

            if (value != 3L) {
                throw new RuntimeException("value=" + value);
            }
        }
    }
}

/*
Output:
value=3
...
value=3
value=1
Exception in thread "main" java.lang.RuntimeException: value=1
    at com.bawi.threads.MyRaceCondition.main(MyRaceCondition.java:33)

*/