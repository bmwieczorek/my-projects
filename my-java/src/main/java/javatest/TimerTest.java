package javatest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerTest implements ActionListener {
    public static void main(String[] args) {
        TimerTest test = new TimerTest();
		new javax.swing.Timer(100, test);
    }

    public void actionPerformed(ActionEvent ev) {
        System.out.println("Timer ticked.");
    }
}
