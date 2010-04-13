package generics.simple.extension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

// service model (without access to backend model)
// package generics.model.service
class PersonBase {}

class Mother extends PersonBase {
    private final String name;

    public Mother(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// backend model
// package generics.model.backend
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Convertable {
    Class<?> value();
}

abstract class ImPersonBase<T extends PersonBase> {
    abstract public String introduce();

    abstract public T convert();
}

@Convertable(Mother.class)
class ImMother extends ImPersonBase<Mother> {
    public String imName;

    public ImMother(String imName) {
        this.imName = imName;
    }

    // used by converter
    public ImMother(Mother mother) {
        this.imName = mother.getName();
    }

    public String introduce() {
        return getClass().getName() + ":" + imName;
    }

    public Mother convert() {
        return new Mother(imName);
    }
}

class ServiceToBackendConverter {
	public static ImPersonBase<?> convert(PersonBase personBase) {
        if (personBase instanceof Mother) {
            return new ImMother((Mother) personBase);
        }
        throw new ConversionException("Conversion not defined for: " + personBase);
    }
}

// package generics.converter
public class GenericAnnotatedConverter {
    private static Map<Class<?>, Class<?>> map = new HashMap<Class<?>, Class<?>>();
    static {
        registerAnnotated(ImMother.class);
    }

    public static void main(String[] args) throws Exception {

        ImMother imMother = new ImMother("Ania");

        // conversion from backend to service model
        Mother mother = imMother.convert();

        // conversion from service to backend model
        Object imConvert = GenericAnnotatedConverter.convert(mother);
        System.out.println(((ImMother) imConvert).introduce());

        ImPersonBase<?> imPersonBase = GenericAnnotatedConverter.convert(mother, ImPersonBase.class);
        System.out.println(imPersonBase.introduce());

        imMother = GenericAnnotatedConverter.convert(mother, ImMother.class);
        System.out.println(imMother.introduce());
    }

    public static Object convert(Object serviceInstance) throws Exception {
        Class<?> serviceClass = serviceInstance.getClass();
        Class<?> backendClass = map.get(serviceClass);
        if (backendClass == null)
            throw new ConversionException("Could not find conversion mapping for " + serviceClass.getName()
                    + ". Register annotated backend element.");
        Constructor<?>[] backendConstructors = backendClass.getConstructors();
        for (Constructor<?> backendConstructor : backendConstructors) {
            Class<?>[] backendParameterTypes = backendConstructor.getParameterTypes();
            for (Class<?> backendParameterType : backendParameterTypes) {
                if (backendParameterType.isAssignableFrom(serviceInstance.getClass())) {
                    return backendClass.cast(backendConstructor.newInstance(serviceInstance));
                }
            }
        }
        throw new ConversionException("Could not find backend constructor for class " + backendClass
                + " with argument of type: " + serviceClass.getName());
    }

    public static <T> T convert(Object serviceInstance, Class<T> backendInterface) throws Exception {
        Class<?> serviceClass = serviceInstance.getClass();
        Class<? extends T> backendClass = map.get(serviceClass).asSubclass(backendInterface);
        Constructor<?>[] backendConstructors = backendClass.getConstructors();
        for (Constructor<?> backendConstructor : backendConstructors) {
            Class<?>[] backendParameterTypes = backendConstructor.getParameterTypes();
            for (Class<?> backendParameterType : backendParameterTypes) {
                if (backendParameterType.isAssignableFrom(serviceInstance.getClass())) {
                    return backendClass.cast(backendConstructor.newInstance(serviceInstance));
                }
            }
        }
        throw new ConversionException("Could not find backend constructor for class " + backendClass
                + " of type " + backendInterface.getName() + " with an argument of type: "
                + serviceClass.getName());
    }

    private static void registerAnnotated(Class<?> backendClass) {
        Convertable annotation = backendClass.getAnnotation(Convertable.class);
        Class<?> serviceClass = annotation.value();
        map.put(serviceClass, backendClass);
    }
}

// package generics.exception
class ConversionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ConversionException(String message) {
        super(message);
    }
}