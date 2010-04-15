package concurency;

class B {
    synchronized void m() throws InterruptedException {
        Thread.sleep(10000);
    }
}

public class Deamons {

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("main bawi thread");
        System.err.println(Thread.currentThread());
		// final B b = new B();

        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    Thread.currentThread().setName("another bawi thread");
                    System.out.println(Thread.currentThread());
                     try {
                        //b.m();
                         Thread.sleep(111000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // the deamon thread will be stopped if the main thread completes
        // the non-deamon one will continue to run even if the main thread
        // completed
        thread.setDaemon(false);
        thread.start();
        //b.m();
        System.err.println(Thread.currentThread());
        //Thread.sleep(10000);
    }

}
