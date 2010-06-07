package serialization;

import javax.swing.JApplet;
import javax.swing.JButton;

public class TestApplet extends JApplet {

    private static final long serialVersionUID = 1L;

    public void init() {
        JButton b1 = new JButton("one");
        JButton b2 = new JButton("two");
        JButton b3 = new JButton("three");
        JButton b4 = new JButton("four");
        JButton b5 = new JButton("five");

        getContentPane().add(b1);
        getContentPane().add(b2);
        getContentPane().add(b3);
        getContentPane().add(b4);
        getContentPane().add(b5);
    }
}