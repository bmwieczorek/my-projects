package designpatterns.flyweight.gof;

public class Letter extends Glyph {
    int code;

    public Letter(int code) {
        this.code = code;
        System.out.println("Created new Letter from code " + code);
    }

    @Override
    public void draw(FontContext context) {
        System.out.print("<font size=\"" + context.getFontSize() + "\">");
        System.out.print((char) code);
        System.out.print("</>");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + code;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Letter other = (Letter) obj;
        if (code != other.code)
            return false;
        return true;
    }
}
