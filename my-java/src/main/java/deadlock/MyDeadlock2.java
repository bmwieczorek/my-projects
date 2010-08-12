package deadlock;


public class MyDeadlock2 {
    private static final Log log = new Log();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info();
            }
        });
        t1.start();
        DeadlockingObject.getInstance();
    }
}
