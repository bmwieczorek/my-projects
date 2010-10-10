package designpatterns.flyweight.gof2;

import static designpatterns.flyweight.gof2.Color.green;
import static designpatterns.flyweight.gof2.Color.red;

public class Context {

    private final int[] fontSizesList = { 1, 2, 3, 2, 1, 3, 2, 1, 2, 3 };
    private final Color[] colorsList = { red, red, red, red, red, green, green, green, green, green };

    private int index;

    public int getFontSize() {
        return fontSizesList[index];
    }

    public Color getColor() {
        return colorsList[index];
    }

    public void next() {
        index++;
    }
}
