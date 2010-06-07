public class MySingleton {

    public int i;

    private MySingleton() {
    }

    public static MySingleton INSTANCE = new MySingleton();

}
