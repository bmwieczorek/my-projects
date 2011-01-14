package spring;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

class Factory {

    /**
     * Get class name from the file, call Class.forName and return Class
     * instance, call clazz.constructor.newInstance(), call
     * clazz.getDeclaredMethods, method.invoke
     */
    Object getBean() throws ClassNotFoundException, IllegalArgumentException, SecurityException,
            InstantiationException, IllegalAccessException, IOException, InvocationTargetException,
            NoSuchMethodException {

        String dataFromFile = fileContentAsString("spring/context.xml");
        String implClassName = dataFromFile.substring(0, dataFromFile.indexOf(','));
        Class<?> clazz = Class.forName(implClassName);
        String paramName = dataFromFile.substring(dataFromFile.indexOf(',') + 1, dataFromFile.indexOf('='));
        String paramValue = dataFromFile.substring(dataFromFile.indexOf('=') + 1);
        String setMethodName = "set" + paramName.substring(0, 1).toUpperCase() + paramName.substring(1);

        Object newInstance = null;

        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == 0) {
                newInstance = clazz.getConstructor().newInstance();

                Method declaredMethod;
                try {
                    declaredMethod = clazz.getDeclaredMethod(setMethodName, int.class);
                    declaredMethod.invoke(newInstance, paramValue);
                } catch (NoSuchMethodException e) {
                }

                try {
                    declaredMethod = clazz.getDeclaredMethod(setMethodName, String.class);
                    declaredMethod.invoke(newInstance, paramValue);
                } catch (NoSuchMethodException e) {
                }

            } else {
                System.out.println(Arrays.asList(parameterTypes));
                newInstance = constructor.newInstance("constructor-song2");
            }
        }
        return newInstance;
    }

    private String fileContentAsString(String fileName) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        byte[] buf = new byte[is.available()];
        is.read(buf);
        return new String(buf);
    }
}

public class Example {

    public static void main(String[] args) throws IllegalArgumentException, SecurityException, ClassNotFoundException,
            InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
            IOException {
        Factory f = new Factory();
        Performer p = (Performer) f.getBean();
        p.perform();
    }

}
