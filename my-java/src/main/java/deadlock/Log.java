package deadlock;

import static deadlock.Utils.sleep;

public class Log {
    public synchronized void info() {
        System.out.println("Pen:" + Thread.currentThread().getName());
        sleep(100);
        DeadlockingObject.getInstance();
    }
}