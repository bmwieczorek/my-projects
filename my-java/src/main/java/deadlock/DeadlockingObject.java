package deadlock;

public class DeadlockingObject {

    private static final Logger logger = Logger.getInstance();

    public static synchronized void getInstance() {
        System.out.println("INSTANCE:" + Thread.currentThread().getName());
        Utils.sleep(50);
        logger.info();
    }
}
