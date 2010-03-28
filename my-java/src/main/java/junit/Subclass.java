package junit;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

class SuperClass {
    void init() throws SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {

        // this.getClass() invoked in super class returns reference to the
        // subclass class instance
        // use reflection to set parameter and invoke methods on subclass
        System.out.println(this.getClass());
        Class<? extends SuperClass> class1 = this.getClass();
        Field field = class1.getDeclaredField("name");
        field.setAccessible(true);
        field.set(this, "Ania");
        class1.getMethod("testMethod").invoke(this);
    }
}

public class Subclass extends SuperClass {
    String name = "";

    public void testMethod() {
        System.out.println("Subclass name:" + name + "!");
    }
}
