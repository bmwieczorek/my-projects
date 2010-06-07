package concurency;

import java.util.HashMap;
import java.util.Map;

public class ConcurrentHasMapAccess {
    static Map<String, String> mapping = new HashMap<String, String>();

    public static void main(String[] args) {

        System.out.println("before");
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread());
                    Thread.yield();
                    try {
                        Thread.sleep(1);
                        put("MyKey");
                        failOnNoKey("MyKey");
                        put("MyKey2");
                        get("MyKey2");
                        failOnSizeNot(2);
                        get("MyKey");
                        failOnNoKey("MyKey2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread());
                    Thread.yield();
                    try {
                        Thread.sleep(1);
                        put("MyKey");
                        failOnNoKey("MyKey");
                        put("MyKey2");
                        get("MyKey2");
                        failOnSizeNot(2);
                        get("MyKey");
                        failOnNoKey("MyKey2");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        thread.start();
        thread2.start();
        System.out.println("after");
    }

    private static String get(String key) {
        return mapping.get(key);
    }

    private static void failOnSizeNot(int size) {
        if (mapping.size() != size) {
            throw new RuntimeException("Size not " + size);
        }
    }

    private static void failOnNoKey(String key) {
        if (!mapping.containsKey(key)) {
            throw new RuntimeException("No key");
        }
    }

    private static void put(String value) {
        mapping.put(value, value);
    }
}
