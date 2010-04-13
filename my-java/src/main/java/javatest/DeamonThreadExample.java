package javatest;

public class DeamonThreadExample extends Thread {
    public void run() {
        while (true) {
            System.out.println("Hello World!");
        }
    }

    public static void main(String[] s) {
        DeamonThreadExample a = new DeamonThreadExample();
        a.setDaemon(true);
        a.start();
    }

}