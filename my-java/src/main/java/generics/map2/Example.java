package generics.map2;

import java.util.HashMap;
import java.util.Map;

abstract class FAF<T extends FA> {

    Map<Class<? extends T>, FAF<? extends T>> map = new HashMap<Class<? extends T>, FAF<? extends T>>();
    {
        map.put(DRFA.class, new DRFAF());
    }

    static FA getFA(FA templateAppender, String customFileName) {
        if (map.containsKey(templateAppender.getClass())) {
            FAF faf = map.get(templateAppender.getClass());
            return faf.createFromTemplate(null, customFileName);
        }
        throw new RuntimeException("FA factory not found for class " + templateAppender.getClass());
    }

    abstract T createFromTemplate(T templateFileAppender, String customFileName);
}

class DRFAF extends FAF<DRFA> {

    @Override
    DRFA createFromTemplate(DRFA templateFileAppender, String customFileName) {
        return null;
    }

}

class FA {
}

class DRFA extends FA {
    public DRFA(String customFileName) {
        System.out.println("DRFA created with file " + customFileName);
    }
}

public class Example {
    public static void main(String[] args) {
        DRFA appender = new DRFA("A");
        FAF.getFA(appender, "AA");
    }
}
