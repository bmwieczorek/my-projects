package concurency.locks;

import static java.lang.String.valueOf;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadersWritersProblem {
    final ReadWriteLock lock = new ReentrantReadWriteLock();

    void readConcurrentWithLimit(int limit) {
        lock.readLock().lock();
        try {
            readWithLimit(limit);
        } finally {
            lock.readLock().unlock();
        }
    }

    void writeConcurrentWithLimit(int limit) {
        lock.writeLock().lock();
        try {
            writeWithLimit(limit);
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void readWithLimit(int limit) {
        for (int i = 0; i < limit; i++) {
            System.out.println(Thread.currentThread().getName() + ": reading with limit " + (limit - i)
                    + "::" + getSecond());
            sleep();
        }
    }

    private void writeWithLimit(int limit) {
        for (int i = 0; i < limit; i++) {
            System.out.println(Thread.currentThread().getName() + ": writing with limit " + (limit - i)
                    + "::" + getSecond());
            sleep();
        }
    }

    public static void main(String[] args) {
        ReadersWritersProblem readersWritersProblem = new ReadersWritersProblem();
        // readersWritersProblem.createReaderThread(readersWritersProblem).start();
        // readersWritersProblem.createReaderThread(readersWritersProblem).start();
        readersWritersProblem.createReaderThreadWithLimit(readersWritersProblem, 3).start();
        sleep();
        readersWritersProblem.createReaderThreadWithLimit(readersWritersProblem, 3).start();
        readersWritersProblem.createWriterThreadWithLimit(readersWritersProblem, 5).start();
        // readersWritersProblem.read();
    }

    private Thread createWriterThreadWithLimit(final ReadersWritersProblem readersWritersProblem,
            final int limit) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                readersWritersProblem.writeConcurrentWithLimit(limit);
            }
        });
    }

    private Thread createReaderThreadWithLimit(final ReadersWritersProblem readersWritersProblem,
            final int limit) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                readersWritersProblem.readConcurrentWithLimit(limit);
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
