package applet;

import java.applet.Applet;
import java.awt.Graphics;

//No need to extend JApplet, since we don't add any components;
//we just paint.
public class HelloWorldApplet extends Applet {

    private static final long serialVersionUID = 1L;

    StringBuffer buffer;

    @Override
    public void init() {
        buffer = new StringBuffer();
        addItem("initializing... ");
        System.out.println("ania");
    }

    @Override
    public void start() {
        addItem("starting... ");
    }

    @Override
    public void stop() {
        addItem("stopping... ");
    }

    @Override
    public void destroy() {
        addItem("preparing for unloading...");
    }

    private void addItem(String newWord) {
        System.out.println(newWord);
        buffer.append(newWord);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        // Draw a Rectangle around the applet's display area.
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        // Draw the current string inside the rectangle.
        g.drawString(buffer.toString(), 5, 15);
    }
}
