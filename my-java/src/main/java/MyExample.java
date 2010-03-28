import java.lang.reflect.InvocationTargetException;

class MyDriverClass {
    static {
        System.out.println("Statically initialized");
        new DriverManager(new MyDriverClass());
    }

    public MyDriverClass() {
        System.out.println("constructed");
    }

}

class DriverManager {
    MyDriverClass myDriverClass;
    public DriverManager(MyDriverClass myDriverClass) {
        this.myDriverClass = myDriverClass;
    }
    static String getConnection(){
        return "AAA";
    }
}

public class MyExample {
    public static void main(String[] args) throws ClassNotFoundException,
            SecurityException, NoSuchMethodException, IllegalArgumentException,
            InstantiationException, IllegalAccessException,
            InvocationTargetException {

        System.out.println("Loading...");
        // MyDriverClass myDriverClass =
        // (MyDriverClass)Class.forName("MyDriverClass").newInstance();
        Class.forName("MyDriverClass");
    }
}
