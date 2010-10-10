package designpatterns.flyweight.gof2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlyweightFactory {

    private Map<Integer, Character> codesToCharacters = new HashMap<Integer, Character>();

    public Character createCharacter(char c) {
        if (codesToCharacters.containsKey(c)) {
            return codesToCharacters.get(c);
        }

        Character newCharacter = new Character(c);
        codesToCharacters.put((int) c, newCharacter);
        return newCharacter;
    }

    public Row createRow(String text) {
        return new Row(toCharacters(text));
    }

    public Column createColumn(List<Row> rows) {
        return new Column(rows);
    }

    private List<Character> toCharacters(String text) {
        List<Character> chars = new ArrayList<Character>();
        for (char c : text.toCharArray()) {
            chars.add(createCharacter(c));
        }
        return chars;
    }

}
