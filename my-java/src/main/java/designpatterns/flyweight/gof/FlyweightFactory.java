package designpatterns.flyweight.gof;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
    Map<Integer, Letter> codes = new HashMap<Integer, Letter>();

    public Glyph create(int code) {
        if (codes.containsKey(code)) {
            return codes.get(code);
        }

        Letter newLetter = new Letter(code);
        codes.put(code, newLetter);
        return newLetter;
    }
}
