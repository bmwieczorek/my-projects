package deadlock;

import static java.lang.Thread.currentThread;

class Ruller {
    private static final Ruller RULLER = new Ruller();

    private Ruller() {
    }

    public static Ruller getInstance() {
        return RULLER;
    }

    public synchronized void ruller() {
        System.out.println("RULLER:" + currentThread());
        Pen.pen();
    }
}

class Pen {
    public static synchronized void pen() {
        System.out.println("PEN:" + currentThread().getName());
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Ruller.getInstance().ruller();
    }
}

public class Deadlock3 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Pen.pen();
            }
        }).start();
        Ruller.getInstance().ruller();
    }
}
