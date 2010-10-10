package designpatterns.flyweight.gof2;

import java.util.List;

public class Column extends Glyph {
    private List<Row> rows;

    public Column(List<Row> rows) {
        this.rows = rows;
    }

    @Override
    public void draw(Context context) {
        for (Row row : rows) {
            startRow();
            row.draw(context);
            endRow();
        }
    }

    private void startRow() {
        System.out.print("<br>");
    }

    private void endRow() {
        System.out.println("</br>");
    }
}
