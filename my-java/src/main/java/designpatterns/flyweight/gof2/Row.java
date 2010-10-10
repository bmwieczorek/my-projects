package designpatterns.flyweight.gof2;

import java.util.List;

public class Row extends Glyph {

    private List<Character> characters;

    public Row(List<Character> characters) {
        this.characters = characters;
    }

    @Override
    public void draw(Context ctx) {
        for (Character character : characters) {
            character.draw(ctx);
        }
    }
}
