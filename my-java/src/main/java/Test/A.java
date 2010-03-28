package Test;

public class A extends Thread {
    public void run() {
        while (true) {
            System.out.println("Hello World!");
        }
    }

    public static void main(String[] s) {
        A a = new A();
        a.setDaemon(true);
        a.start();
    }

}