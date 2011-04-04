package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class DemoClass {
    private String name;
    protected int age;

    DemoClass() {
    }

    public DemoClass(@SuppressWarnings("unused") long l) {
    }

    void myMethod() {
        System.out.println(name);
    }
}

public class ShowObjectInfo {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("reflection.DemoClass");

            // has access only to public members
            for (Constructor<?> constructor : clazz.getConstructors())
                System.out.println(constructor);
            for (Field field : clazz.getFields())
                System.out.println(field);
            for (Method method : clazz.getMethods())
                System.out.println(method);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        DemoClass demoClass = new DemoClass();
        demoClass.age = 5;
        Class<? extends DemoClass> demoClassObj = demoClass.getClass();
        for (Field field : demoClassObj.getDeclaredFields()) {
            try {
                System.out.println(field + ":" + field.get(demoClass));
                field.set(demoClass, 55);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println(demoClass.age);
    }
}
