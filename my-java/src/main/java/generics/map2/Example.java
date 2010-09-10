package generics.map2;

import java.util.HashMap;
import java.util.Map;

abstract class FAF<T extends FA> {
    abstract T createFromTemplate(T templateFileAppender, String customFileName);
}

class DRFAF extends FAF<DRFA> {
    @Override
    DRFA createFromTemplate(DRFA templateFileAppender, String customFileName) {
        return new DRFA(customFileName);
    }
}

abstract class FA {
}

class DRFA extends FA {
    public DRFA(String customFileName) {
        System.out.println("DRFA created with file " + customFileName);
    }
}

class FAFP {
    static Map<Class<? extends FA>, FAF<? extends FA>> map = new HashMap<Class<? extends FA>, FAF<? extends FA>>();
    static {
        map.put(DRFA.class, new DRFAF());
    }

    @SuppressWarnings("unchecked")
    static <T extends FA> T getFA(T templateAppender, String customFileName) {
        if (map.containsKey(templateAppender.getClass())) {
            FAF<T> faf = (FAF<T>) map.get(templateAppender.getClass());
            return faf.createFromTemplate(templateAppender, customFileName);
        }
        throw new RuntimeException("FA factory not found for class " + templateAppender.getClass());
    }
}

public class Example {
    public static void main(String[] args) {
        DRFA appender = new DRFA("A");
        DRFA fa = FAFP.getFA(appender, "AA");
        System.out.println(fa);
    }
}
