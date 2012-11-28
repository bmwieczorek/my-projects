package generics;

import java.util.Arrays;
import java.util.List;

class Figure {
}

class Circle extends Figure {
}

public class Example<T> {

    public List<T> printExactTypes(List<T> list) {
        return list;
    }

    public List<? extends T> printExtendingTypes(List<? extends T> list) {
        return list;
    }

    // public static void compare(List<? extends Comparable<?>> list) {
    // }

    public static <E extends Comparable<E>> E compare(List<? extends E> list) {
        return null;
    }

    public static void main(String[] args) {
        // Even though Circle is a Figure (Circle extends Figure), List<Circle>
        // is NOT List<Figure> (List<Circle>
        // doesn't extend List<Figure>). In order to add List<Circle> , you need
        // to change from parameter type from
        // List<Figure> to List<? extends Figure>

        Example<Figure> example = new Example<Figure>();
        List<Figure> figures = Arrays.asList(new Figure(), new Circle());
        List<Circle> circles = Arrays.asList(new Circle());

        example.printExactTypes(figures);
        // geneficClass.printExactType(integers); // doesn't compile

        example.printExtendingTypes(figures);
        List<? extends Figure> printExtendingTypes = example.printExtendingTypes(circles);
        printExtendingTypes.clear();
    }
}
