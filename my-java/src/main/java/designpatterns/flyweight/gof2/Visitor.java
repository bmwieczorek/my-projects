package designpatterns.flyweight.gof2;

public interface Visitor {

    void visit(Character character, Context ctx);

    void visit(Row character, Context ctx);
}
