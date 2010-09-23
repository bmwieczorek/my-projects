package concurency.locks;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.lang.Thread.currentThread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWEx {
    final ReadWriteLock lock = new ReentrantReadWriteLock();

    void read(int limit) {
        lock.readLock().lock();
        try {
            doWithLimit("read", limit);
        } finally {
            lock.readLock().unlock();
        }
    }

    void write(int limit) {
        lock.writeLock().lock();
        try {
            doWithLimit("write", limit);
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void doWithLimit(String action, int limit) {
        for (int i = limit; i > 0; i--) {
            String thread = currentThread().getName();
            out.println(thread + "," + action + ",left " + i + sec());
            sleepSeconds(1);
        }
    }

    public static void main(String[] args) {
        RWEx rw = new RWEx();
        rw.createReaderThread(rw, 3).start();
        sleepSeconds(1);
        rw.createReaderThread(rw, 3).start();
        rw.createWriterThread(rw, 3).start();
    }

    private Thread createWriterThread(final RWEx rwEx, final int limit) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                rwEx.write(limit);
            }
        });
    }

    private Thread createReaderThread(final RWEx rwEx, final int limit) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                rwEx.read(limit);
            }
        });
    }

    private static void sleepSeconds(int n) {
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
