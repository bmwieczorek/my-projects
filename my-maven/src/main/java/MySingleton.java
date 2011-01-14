public final class MySingleton {

    public static final MySingleton INSTANCE = new MySingleton();
    private int i;

    private MySingleton() {
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

}
