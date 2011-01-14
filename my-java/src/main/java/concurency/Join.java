package concurency;

public class Join {

    public static void main(String[] args) throws InterruptedException {
        final Thread t1;
        final Thread t2;

        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[t1]:starting");
                for (int i = 0; i < 3; i++) {
                    System.out.print("x");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("[t1]:stopping");
            }
        });

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[t2]:starting");
                for (int i = 0; i < 5; i++) {
                    System.out.print("y");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("[t2]:stopping");
            }
        });

        t1.start();
        t2.start();

        // main thread will wait for t1 and t2 to finish before it continues to
        // do next job
        // otherwise (without t1.join() the main thread would print done before
        // t1 finishes
        t1.join();
        t2.join();
        System.out.println("done");
    }

}
