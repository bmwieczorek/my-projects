package junit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public final class InitializeClass {

    private InitializeClass() {
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {

        Class<?> cl = Class.forName(MyJunitTest.class.getName());
        Constructor<?> defConstr = cl.getConstructor();
        defConstr.newInstance();
        Constructor<?> constr = cl.getConstructor(String.class);
        MyJunitTest instance = (MyJunitTest) constr.newInstance("with string");
        instance.say("method with string");
        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods) {
            Test annotation = method.getAnnotation(Test.class);
            if (annotation != null) {
                System.out.println("Method with @Test: " + method.getName());
            }
        }
    }
}
