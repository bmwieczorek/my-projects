package reflection;

interface If {
}

class A implements If {

}

class AA extends A {

}

public class InstanceOfInterface {

    @SuppressWarnings("cast")
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Class<?>[] interfaces = A.class.getInterfaces();
        for (Class<?> class1 : interfaces) {
            System.out.println(class1.getName());
        }
        A newInstance = A.class.newInstance();
        Class<?>[] interfaces2 = newInstance.getClass().getInterfaces();
        for (Class<?> class1 : interfaces2) {
            System.out.println(class1.getName());
        }
        if (newInstance instanceof If)
            System.out.println("newInstance " + newInstance.toString() + " is instance of If interface");

        AA newInstance2 = AA.class.newInstance();

        if (newInstance2 instanceof A)
            System.out.println("newInstance " + newInstance2.toString() + " is instance of If interface");

    }

}
