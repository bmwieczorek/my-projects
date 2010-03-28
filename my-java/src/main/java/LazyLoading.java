class Something {
    private Something() {
        System.out.println("Siostra");
    }

    static {
        System.out.println("Mama");
    }

    private static class LazyHolder {
        static {
            System.out.println("Loaded");
        }

        private static final Something something = new Something();
    }

    public static Something getInstance() {
        return LazyHolder.something;
    }
}

class Singleton {
    public final static Singleton INSTANCE = new Singleton();

    // Protected constructor is sufficient to suppress unauthorized calls to the
    // constructor
    protected Singleton() {
        System.out.println("Tata");
    }
}

public class LazyLoading {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("Something");
        // Class.forName("Singleton");
        // Something.getInstance();
        // Something.getInstance();
    }

}
