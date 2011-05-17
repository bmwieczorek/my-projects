package concurency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalWithMultipleThreads {

    private static ThreadLocal<String> myThreadLocal = new ThreadLocal<String>() {
        // private static ThreadLocal<String> myThreadLocal = new InheritableThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "One";
        };

    };

    public static void main(String[] args) {
        myThreadLocal.set("main");
        ExecutorService service = Executors.newFixedThreadPool(3);
        Runnable command = new Runnable() {

            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                long id = Thread.currentThread().getId();
                String value = myThreadLocal.get();
                String data = "[" + name + ":" + id + "=" + value + "]";
                System.out.println(data);
                myThreadLocal.set(data);
            }
        };

        for (int i = 0; i < 3; i++) {
            service.execute(command);
        }

        service.shutdown();
    }

}
