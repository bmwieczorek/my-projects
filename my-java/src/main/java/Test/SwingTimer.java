package Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTimer implements ActionListener{
        public static void main(String[] args){
          TimerTest test = new TimerTest();
          javax.swing.Timer timer = new javax.swing.Timer(1000,test);
          timer.start();
         
        }
        public void actionPerformed(ActionEvent ev){
          System.out.println("Timer ticked.");
        }
}
