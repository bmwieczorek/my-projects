package designpatterns.flyweight.gof;

public class FontContext {
    private int fontSize;

    public FontContext(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSize() {
        return fontSize;
    }

}
