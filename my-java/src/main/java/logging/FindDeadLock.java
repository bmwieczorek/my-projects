package logging;

import org.apache.log4j.Logger;

public class FindDeadLock {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1:" + Thread.currentThread().getName());
                Logger.getLogger(getClass()).info(new FindDeadLock());
                System.out.println("2:" + Thread.currentThread().getName());
            }
        }).start();
        System.out.println("3:" + Thread.currentThread().getName());
        DeadLockingObject.getInstance();
        System.out.println("4:" + Thread.currentThread().getName());
    }

    @Override
    public String toString() {
        /* now we are inside log4j, try to create a DeadLockingObject */
        DeadLockingObject.getInstance();
        return "Created DeadlockObject, returning string";
    }
}
