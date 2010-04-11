package generics.extension;

import generics.extension.model.backend.Convertable3;
import generics.extension.model.backend.ImMother3;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class GenericAnnotatedConverter {

    private static Map<Class<?>, Class<?>> map = new HashMap<Class<?>, Class<?>>();

    static {
        registerAnnotated(ImMother3.class);
    }

    public static Object convert(Object serviceInstance) {
        Class<?> backendClass = map.get(serviceInstance.getClass());
        Constructor<?>[] backendConstructors = backendClass.getConstructors();
        for (Constructor<?> backendConstructor : backendConstructors) {
            Class<?>[] backendParameterTypes = backendConstructor.getParameterTypes();
            for (Class<?> backendParameterType : backendParameterTypes) {
                if (backendParameterType.isAssignableFrom(serviceInstance.getClass())) {
                    try {
                        return backendClass.cast(backendConstructor.newInstance(serviceInstance));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        throw new RuntimeException("Conversion exception");
    }

    public static <T> T convert(Object serviceInstance, Class<T> backendInterface) {
        T backendInstance = null;
        Class<? extends T> backendClass = map.get(serviceInstance.getClass()).asSubclass(backendInterface);
        Constructor<?>[] backendConstructors = backendClass.getConstructors();
        for (Constructor<?> backendConstructor : backendConstructors) {
            Class<?>[] backendParameterTypes = backendConstructor.getParameterTypes();
            for (Class<?> backendParameterType : backendParameterTypes) {
                if (backendParameterType.isAssignableFrom(serviceInstance.getClass())) {
                    try {
                        backendInstance = backendClass.cast(backendConstructor.newInstance(serviceInstance));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return backendInstance;
    }

    public static void registerAnnotated(Class<?> backendClass) {
        Convertable3 annotation = backendClass.getAnnotation(Convertable3.class);
        Class<?> serviceClass = annotation.value();
        map.put(serviceClass, backendClass);
    }

}
