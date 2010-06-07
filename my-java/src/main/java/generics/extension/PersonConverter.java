package generics.extension;

import generics.extension.model.backend.ImPerson;
import generics.extension.model.service.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PersonConverter {

    public static <T extends ImPerson> T convert(Person person, Class<T> imPersonClass) {
        T imPerson = null;
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
}
