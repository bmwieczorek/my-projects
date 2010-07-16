package com.bawi;

public class MyServicePrinter {

    private MyCorePrinter myCorePrinter = new MyCorePrinter();

    public void printWar() {
        System.out.print("Service: ");
        myCorePrinter.printCore();
    }

    public void printWar(String user) {
        System.out.print("Service user: ");
        myCorePrinter.printCore(user);
    }
}
