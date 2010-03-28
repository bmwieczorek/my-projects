package junit;

import java.lang.reflect.InvocationTargetException;

public class Example {

    public static void main(String[] args) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Subclass s = new Subclass();
        s.init();

    }

}
