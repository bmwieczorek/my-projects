package designpatterns.flyweight.gof2;

public interface VisitedElement {

    VisitedElement withVisitor(Visitor visitor);

}
