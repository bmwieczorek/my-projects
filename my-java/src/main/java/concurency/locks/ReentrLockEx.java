package concurency.locks;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrLockEx {

    final Lock lock = new ReentrantLock();

    void keepLock(int limit) {
        lock.lock();
        out.println(threadName() + "acquired and keeping lock+ ");
        try {
            for (int i = limit; i > 0; i--) {
                out.println(threadName() + "keep lock," + i + sec());
                sleepSeconds(1);
            }
        } finally {
            lock.unlock();
        }

    }

    void tryToAcquireLock() {
        out.println(threadName() + "trying to aquire lock ");
        boolean result = lock.tryLock();
        try {
            if (result)
                out.println(threadName() + "aquired lock ");
            else
                out.println(threadName() + "failed to aquire");
        } finally {
            if (result)
                lock.unlock();
        }

    }

    void acquireLock() {
        out.println(threadName() + "aquire lock ");
        lock.lock();
        try {
            out.println(threadName() + "aquired ");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrLockEx rlEx = new ReentrLockEx();
        rlEx.createKeeping(rlEx, 5).start();
        sleepSeconds(1);
        rlEx.createTryToAcquire(rlEx).start();
        sleepSeconds(1);
        rlEx.createAquiring(rlEx).start();
    }

    Thread createAquiring(final ReentrLockEx rlEx) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                rlEx.acquireLock();
            }
        });
    }

    Thread createKeeping(final ReentrLockEx rlEx, final int limit) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                rlEx.keepLock(limit);
            }
        });
    }

    Thread createTryToAcquire(final ReentrLockEx rlEx) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                rlEx.tryToAcquireLock();
            }
        });
    }

    static String threadName() {
        return Thread.currentThread().getName() + ",";
    }

    static void sleepSeconds(int n) {
        try {
            Thread.sleep(1000 * n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static String sec() {
        long currentTimeMillis = currentTimeMillis();
        long value = currentTimeMillis / 1000;
        return ",second " + (value - (value / 100 * 100));
    }

}
