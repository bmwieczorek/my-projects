package inheritance;

import java.lang.reflect.Field;

class Sup {

    public Sup() throws SecurityException {
        Class<? extends Sup> clazz = this.getClass();
        System.out.println("[Superclass] " + clazz);
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println("[Superclass] " + field);
        }
    }
}

public class SuperSubExample extends Sup {
    String name = "";

    public SuperSubExample() {
        System.out.println("[Subclass] name:" + name);
    }

    public static void main(String[] args) {
        new SuperSubExample();
    }
}
