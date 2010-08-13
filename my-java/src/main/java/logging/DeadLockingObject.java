package logging;

import org.apache.log4j.Logger;

public final class DeadLockingObject {
    private static final Logger LOG = Logger.getLogger(DeadLockingObject.class.getName());
    private static DeadLockingObject singleton = new DeadLockingObject();

    private DeadLockingObject() {
    }

    public synchronized static DeadLockingObject getInstance() {
        try {
            // to make the deadlock occur
            System.out.println("a:" + Thread.currentThread().getName());
            Thread.sleep(100);
            System.out.println("b:" + Thread.currentThread().getName());
        } catch (InterruptedException e) {

        }
        System.out.println("c:" + Thread.currentThread().getName());
        LOG.info("Returning nice singleton");
        System.out.println("d:" + Thread.currentThread().getName());
        return singleton;

    }
}
