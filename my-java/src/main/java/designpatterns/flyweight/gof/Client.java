package designpatterns.flyweight.gof;

public class Client {
    private static Row[] rows = new Row[4];
    private static FlyweightFactory flyweightFactory = new FlyweightFactory();

    public static void main(String[] args) {

        System.out.println("**********");
        System.out.println("***ANIA***");
        System.out.println("**********");

        populateRows();
        printRows();
    }

    private static void populateRows() {
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Row();
            for (int code = 65; code < 68; code++) {
                rows[i].glyphs.add(flyweightFactory.create(code));
            }
        }
    }

    private static void printRows() {
        startDoc();
        int fontSize = 1;
        for (Row row : rows) {
            startRow();
            row.draw(new FontContext(fontSize++));
            endRow();
        }
        endDoc();
    }

    private static void startDoc() {
        System.out.println("<html><body>");
    }

    private static void endDoc() {
        System.out.println("</body></html>");
    }

    private static void endRow() {
        System.out.println("</br>");
    }

    private static void startRow() {
        System.out.print("<br>");
    }
}
