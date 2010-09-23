package concurency.locks;

import static java.lang.String.valueOf;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyReentrantLock {

    final Lock lock = new ReentrantLock();

    void keepLockWithLimit(int limit) {
        lock.lock();
        try {
            for (int i = 0; i < limit; i++) {
                System.out.println(Thread.currentThread().getName() + ": reading with limit " + (limit - i)
                        + "::" + getSecond());
                sleep();
            }
        } finally {
            lock.unlock();
        }

    }

    void tryToAcquireLock() {
        System.out.println(Thread.currentThread().getName() + " trying ... ");
        boolean result = lock.tryLock();
        try {
            if (result)
                System.out.println(Thread.currentThread().getName() + " aquired ");
            else
                System.out.println(Thread.currentThread().getName() + " failed to aquire");
        } finally {
            if (result)
                lock.unlock();
        }

    }

    void acquireLock() {
        System.out.println(Thread.currentThread().getName() + " trying ... ");
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " aquired ");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyReentrantLock myReentrantLock = new MyReentrantLock();
        myReentrantLock.createKeepingThreadWithLimit(myReentrantLock, 5).start();
        sleep();
        // myReentrantLock.createTryingThreadWithLimit(myReentrantLock).start();
        myReentrantLock.createAquiringThreadWithLimit(myReentrantLock).start();
    }

    private Thread createAquiringThreadWithLimit(final MyReentrantLock myReentrantLock) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                myReentrantLock.acquireLock();
            }
        });
    }

    private Thread createKeepingThreadWithLimit(final MyReentrantLock myReentrantLock, final int limit) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                myReentrantLock.keepLockWithLimit(limit);
            }
        });
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static String getSecond() {
        return valueOf(System.currentTimeMillis() / 1000);
    }

}
