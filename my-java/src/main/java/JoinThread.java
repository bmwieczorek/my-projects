public class JoinThread implements Runnable {

    static Thread currentThread = null;

    public static void main(String[] args) {
        currentThread = Thread.currentThread();
        System.out.println(currentThread.getName() + "XXX");
        Thread t = new Thread(new JoinThread());
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println("XXX" + e.getLocalizedMessage());
        }
        System.out.println(currentThread.getName() + "XXX");
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(4000);
            System.out.println(Thread.currentThread().getName());
            currentThread.interrupt();
            Thread.sleep(4000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
