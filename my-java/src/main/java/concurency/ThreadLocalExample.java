package concurency;

public class ThreadLocalExample {
    private static Float i = 0f;

    private static ThreadLocal<Float> value = new ThreadLocal<Float>() {
        @Override
        protected Float initialValue() {
            return 0f;
        }
    };

    public static void main(String[] args) throws InterruptedException {
        final Thread t1;
        final Thread t2;

        t1 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    i = i + 0.1f;
                    value.set(value.get() + 0.1f);
                    // System.out.println("i=" + i);
                    System.err.println("value=" + value.get());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        });

        t2 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    i = i + 1;
                    value.set(value.get() + 1f);
                    // System.out.println("ii=" + i);
                    System.out.println("valuevalue=" + value.get());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
        });

        t1.start();
        t2.start();
    }
}
