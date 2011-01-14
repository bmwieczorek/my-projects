class MyDestroyClass {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalizing");
        super.finalize();
    }
}

public class Destroy {
    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            new MyDestroyClass();
        }
    }
}
