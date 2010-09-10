package generics.map;

import java.util.HashMap;
import java.util.Map;

abstract class FAF {

    static Map<Class<? extends FA>, FAF> map = new HashMap<Class<? extends FA>, FAF>();
    static {
        map.put(DRFA.class, new DRFAF());
    }

    static FA getFA(FA appender) {
        if (map.containsKey(appender.getClass())) {
            FAF faf = map.get(appender.getClass());
            return faf.create();
        }
        throw new RuntimeException("FA factory not found for class " + appender.getClass());
    }

    abstract FA create();
}

class DRFAF extends FAF {
    @Override
    FA create() {
        return new DRFA();
    }
}

class FA {
}

class DRFA extends FA {
    public DRFA() {
        System.out.println("DRFA created");
    }
}

public class Example {
    public static void main(String[] args) {
        DRFA appender = new DRFA();
        FAF.getFA(appender);
    }
}
