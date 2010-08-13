package deadlock;

public class MyDeadlock2 {
    private static final Logger logger = Logger.getInstance();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info();
                DeadlockingObject.getInstance();
            }
        });
        t1.start();
        DeadlockingObject.getInstance();
    }
}
