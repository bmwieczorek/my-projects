package deadlock;

public class DeadlockingObject {

    private static final Log log = new Log();

    public static synchronized void getInstance() {
        System.out.println("Ruller:" + Thread.currentThread().getName());
        Utils.sleep(50);
        log.info();

    }

}
