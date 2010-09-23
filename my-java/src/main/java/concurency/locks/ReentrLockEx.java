package concurency.locks;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.lang.Thread.currentThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrLockEx {

    final Lock lock = new ReentrantLock();

    void keepLock(int limit) {
        String thread = currentThread().getName();
        lock.lock();
        out.println(thread + ",acquired and keeping lock+ ");
        try {
            for (int i = limit; i > 0; i--) {

                out.println(thread + ",keep lock, left " + i + " " + sec());
                sleepSeconds(1);
            }
        } finally {
            lock.unlock();
        }

    }

    void tryToAcquireLock() {
        String thread = currentThread().getName();
        out.println(thread + ",trying to aquire lock ");
        boolean result = lock.tryLock();
        try {
            if (result)
                out.println(thread + ",aquired lock ");
            else
                out.println(thread + ",failed to aquire");
        } finally {
            if (result)
                lock.unlock();
        }

    }

    void acquireLock() {
        String thread = currentThread().getName();
        out.println(thread + " aquire lock ");
        lock.lock();
        try {
            System.out.println(thread + " aquired ");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrLockEx rlEx = new ReentrLockEx();
        rlEx.createKeeping(rlEx, 5).start();
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

    Thread createTryToAcquire(final ReentrLockEx rlEx, final int limit) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                rlEx.keepLock(limit);
            }
        });
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
        return ", second " + (value - (value / 100 * 100));
    }

}
