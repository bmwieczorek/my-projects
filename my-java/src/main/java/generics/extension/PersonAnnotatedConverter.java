package generics.extension;

import generics.extension.model.backend.Convertable;
import generics.extension.model.backend.ImMother;
import generics.extension.model.backend.ImPerson;
import generics.extension.model.service.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class PersonAnnotatedConverter {

    private static Map<Class<? extends Person>, Class<? extends ImPerson>> map = new HashMap<Class<? extends Person>, Class<? extends ImPerson>>();

    static {
        register(ImMother.class);
    }

    public static ImPerson convert(Person person) {
        ImPerson imPerson = null;
        Class<? extends ImPerson> imPersonClass = map.get(person.getClass());
        Constructor<?>[] imPersonConstructors = imPersonClass.getConstructors();
        for (Constructor<?> imPersonConstructor : imPersonConstructors) {
            Class<?>[] imPersonParameterTypes = imPersonConstructor.getParameterTypes();
            for (Class<?> imPersonParameterType : imPersonParameterTypes) {
                if (imPersonParameterType.isAssignableFrom(person.getClass())) {
                    try {
                        imPerson = imPersonClass.cast(imPersonConstructor.newInstance(person));
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
        return imPerson;
    }

    public static void register(Class<? extends ImPerson> imPersonClass) {
        Convertable annotation = imPersonClass.getAnnotation(Convertable.class);
        Class<? extends Person> personClass = annotation.value();
        map.put(personClass, imPersonClass);
    }

}
