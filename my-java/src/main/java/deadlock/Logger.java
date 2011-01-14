package deadlock;

import static deadlock.Utils.sleep;

public class Logger {

    private static final Logger LOG = new Logger();

    private Logger() {
    }

    public static Logger getInstance() {
        return LOG;
    }

    public synchronized void info() {
        System.out.println("INFO:" + Thread.currentThread().getName());
        sleep(100);
        DeadlockingObject.getInstance();
    }
}