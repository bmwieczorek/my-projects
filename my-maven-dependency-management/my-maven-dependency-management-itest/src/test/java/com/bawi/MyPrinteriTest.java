package com.bawi;

import org.junit.Test;

public class MyPrinteriTest {

    private MyServicePrinter myWarPrinter = new MyServicePrinter();
    private HighlightUtils highlightUtils = new HighlightUtils();

    @Test
    public void printItest() {
        highlightUtils.print();
        System.out.print("Itest:");
        myWarPrinter.printWar();
        highlightUtils.print();
    }

    @Test
    public void printItestUser() {
        highlightUtils.print();
        String user = "ania";
        System.out.print("Itest user:");
        myWarPrinter.printWar(user);
        highlightUtils.print();
    }
}
