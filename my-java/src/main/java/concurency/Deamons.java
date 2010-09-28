package concurency;

public class Deamons {

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("main bawi thread");
        System.err.println(Thread.currentThread() + " starting");

        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        Thread.currentThread().setName("non-deamon thread is still alive");
                        System.out.println(Thread.currentThread());
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            System.out.println(Thread.currentThread() + " exception: " + e.getCause());
                        }
                    }
                } finally {
                    System.out.println(Thread.currentThread() + " will not enter");
                }
            }
        });

        // the deamon thread will be stopped if the main thread completes
        // the non-deamon one will continue to run even if the main thread
        // completed

        // thread.setDaemon(false);
        thread.setDaemon(false);
        thread.start();

        Thread.sleep(3000);

        System.err.println(Thread.currentThread() + " finished");
    }
}
