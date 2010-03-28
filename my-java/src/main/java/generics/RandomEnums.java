package generics;


enum City {
    Amsterdam, Krakow;
}
class A {
    A() {
        System.out.println(getClass().getName());
    }
}

class Aa extends A {
}

class Aaa extends Aa {
}

public class RandomEnums {

    private static <T extends Aa> T instantiate(Class<T> clazz)
            throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }

    private static <T extends Enum<T>> T randomEnum(Class<T> enumClass) {
        T[] enumConstants = enumClass.getEnumConstants();
        return enumConstants[0];
    }

    public static void main(String[] args) throws InstantiationException,
            IllegalAccessException {
        // A instantiate = instantiate(A.class);
        // Aa instantiate2 =
        instantiate(Aa.class);
        // Aaa instantiate3 =
        instantiate(Aaa.class);
        
        System.out.println(randomEnum(City.class));
    }

}
