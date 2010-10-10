package designpatterns.flyweight.gof2;

public class Character extends Glyph implements VisitedElement {
    private char c;
    private Visitor visitor;

    public Character(char c) {
        this.c = c;
        System.out.println("Created new character " + c);
    }

    @Override
    public void draw(Context ctx) {
        visitor.visit(this, ctx);
        ctx.next();
    }

    public void next(Context context) {
        context.next();
    }

    @Override
    public Character withVisitor(Visitor visitor) {
        this.visitor = visitor;
        return this;
    }

    public char getC() {
        return c;
    }

}
