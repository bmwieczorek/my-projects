package logging;

public class Deadlock {

    private final static Log log = new Log();

    static class Log {
        public synchronized void info(Object object) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    static class Dlo {
        private static final Dlo DLO = new Dlo();

        public static synchronized Dlo getInstance() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("BBB");
            return DLO;
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                log.info(new Deadlock());
            }
        }).start();

        Dlo.getInstance();

    }

    @Override
    public String toString() {
        log.info("AAA");
        return "AAA";
    }
}
