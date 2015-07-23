package countdownlatch;

import java.util.concurrent.CountDownLatch;

import org.junit.Assert;
import org.junit.Test;

public class CountDownLatchTest {

    State state = new State();

    class State {
        public int i;
    }

    class Worker implements Runnable {

        private final State state;

        public Worker(State state) {
            this.state = state;
        }

        @Override
        public void run() {
            state.i++;

        }

    }

    class CountDownLatchWorker extends Worker {
        private final CountDownLatch done;

        public CountDownLatchWorker(State state, CountDownLatch done) {
            super(state);
            this.done = done;
        }

        @Override
        public void run() {
            super.run();
            done.countDown();
        }
    }

    class CountDownLatchWorker2 extends Worker {
        private final CountDownLatch start;
        private final CountDownLatch done;

        public CountDownLatchWorker2(State state, CountDownLatch start, CountDownLatch done) {
            super(state);
            this.start = start;
            this.done = done;
        }

        @Override
        public void run() {
            try {
                start.await();
                super.run();
                done.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testWithoutSynchronization() {
        new Thread(new Worker(state)).start();
        new Thread(new Worker(state)).start();
        // Assert.assertEquals(2, state.i); // fails most of time
    }

    @Test
    public void testWithDelay() throws InterruptedException {
        new Thread(new Worker(state)).start();
        new Thread(new Worker(state)).start();
        Thread.sleep(1000);
        Assert.assertEquals(2, state.i);
    }

    @Test
    public void testWaitUntilWorkersDone() throws InterruptedException {
        CountDownLatch done = new CountDownLatch(2); // wait for two workers to complete
        new Thread(new CountDownLatchWorker(state, done)).start();
        new Thread(new CountDownLatchWorker(state, done)).start();
        done.await();
        Assert.assertEquals(2, state.i);
    }

    @Test
    public void testUnsyncSetupButWaitUntilWorkersDone() throws InterruptedException {
        CountDownLatch done = new CountDownLatch(2);
        new Thread(new CountDownLatchWorker(state, done)).start();
        new Thread(new CountDownLatchWorker(state, done)).start();
        state.i = 10;
        done.await();
        // Assert.assertEquals(12, state.i); // fails most of time
    }

    @Test
    public void testSetupBeforeWorkersStartAndWaitUntilWorkersDone() throws InterruptedException {
        CountDownLatch start = new CountDownLatch(1); // wait for (one) main workers to complete setup
        CountDownLatch done = new CountDownLatch(2);
        new Thread(new CountDownLatchWorker2(state, start, done)).start();
        new Thread(new CountDownLatchWorker2(state, start, done)).start();
        state.i = 10;
        start.countDown();
        done.await();
        Assert.assertEquals(12, state.i);
    }

}
