package designpatterns.flyweight.gof;

import java.util.ArrayList;
import java.util.List;

public class Row extends Glyph {

    List<Glyph> glyphs = new ArrayList<Glyph>();

    @Override
    public void draw(FontContext context) {
        for (Glyph glyph : glyphs) {
            glyph.draw(context);
        }
    }
}
