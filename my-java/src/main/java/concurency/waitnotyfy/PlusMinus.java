package concurency.waitnotyfy;

import java.util.concurrent.TimeUnit;

public class PlusMinus {
    boolean flag;

    synchronized void plus() throws InterruptedException {
        System.out.println("+");
        TimeUnit.MILLISECONDS.sleep(200);
        flag = true;
        System.out.println("+before notify");
        notify();
        System.out.println("+after notify");
        while (flag) {
            System.out.println("+in while before wait");
            wait();
            System.out.println("+in while after wait");
        }
    }

    synchronized void minus() throws InterruptedException {
        System.out.println("-before while");
        while (!flag) {
            System.out.println("-in while before wait");
            wait();
            System.out.println("-in while after wait");
        }
        System.out.println("-");
        TimeUnit.MILLISECONDS.sleep(200);
        flag = false;
        System.out.println("-before notify");
        notify();
        System.out.println("-after notify");
    }

    public static void main(String[] args) {
        final PlusMinus pm = new PlusMinus();
        createPlusThread(pm).start();
        createMinusThread(pm).start();

    }

    private static Thread createPlusThread(final PlusMinus pm) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                while (true)
                    try {
                        pm.plus();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        });
    }

    private static Thread createMinusThread(final PlusMinus pm) {
        return new Thread(new Runnable() {

            @Override
            public void run() {
                while (true)
                    try {
                        pm.minus();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        });
    }

}
