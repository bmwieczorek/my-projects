package designpatterns.flyweight.gof2;

public class Character extends Glyph {
    private char c;

    public Character(char c) {
        this.c = c;
        System.out.println("Created new character " + c);
    }

    @Override
    public void draw(Context ctx) {
        System.out.print("<font size=\"" + ctx.getFontSize() + "\" color=\"" + ctx.getColor() + "\">" + c
                + "</font>");
        ctx.next();
    }

    public void next(Context context) {
        context.next();
    }

}
