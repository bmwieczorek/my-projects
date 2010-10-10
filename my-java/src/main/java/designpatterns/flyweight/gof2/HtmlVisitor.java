package designpatterns.flyweight.gof2;

public class HtmlVisitor implements Visitor {

    @Override
    public void visit(Character character, Context ctx) {
        System.out.print("<font size=\"" + ctx.getFontSize() + "\" color=\"" + ctx.getColor() + "\">"
                + character.getC() + "</font>");
    }

    @Override
    public void visit(Row character, Context ctx) {
    }

}
